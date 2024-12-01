package es.deusto.sd.strava.sd_strava.service;

import es.deusto.sd.strava.sd_strava.dao.UserProfileRepository;
import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import es.deusto.sd.strava.sd_strava.external.FacebookGateway;
import es.deusto.sd.strava.sd_strava.external.GoogleGateway;
import es.deusto.sd.strava.sd_strava.external.IAuthPlatformGateway;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.deusto.sd.strava.sd_strava.Factory.AuthGatewayFactory;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    private final Map<String, UserProfile> tokenStore = new ConcurrentHashMap<>();
    private final UserProfileRepository userProfileRepository;
    private final AuthGatewayFactory authGatewayFactory;

    public AuthService(UserProfileRepository userProfileRepository, AuthGatewayFactory authGatewayFactory) {
        this.userProfileRepository = userProfileRepository;
        this.authGatewayFactory = authGatewayFactory;
    }

    public Optional<String> login(String email) {
        UserProfile user = userProfileRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return Optional.empty();
        }

        String platform = user.getRegistrationPlatformUsed();
        IAuthPlatformGateway gateway = authGatewayFactory.getGateway(platform);

        String token = gateway.authenticate(email);

        if (token != null) {
            tokenStore.put(token, user);
            return Optional.of(token);
        }

        return Optional.empty();
    }

    public boolean logout(String token) {
        return tokenStore.remove(token) != null;
    }

    public UserProfile getUserByToken(String token) {
        return tokenStore.get(token);
    }
}
