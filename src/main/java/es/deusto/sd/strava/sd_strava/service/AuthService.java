package es.deusto.sd.strava.sd_strava.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import es.deusto.sd.strava.sd_strava.external.FacebookGateway;
import es.deusto.sd.strava.sd_strava.external.GoogleGateway;
import org.springframework.stereotype.Service;

import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import es.deusto.sd.strava.sd_strava.dao.UserProfileRepository;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final Map<String, UserProfile> tokenStore = new ConcurrentHashMap<>();
    private final UserProfileRepository userProfileRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public AuthService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public Optional<String> login(String email) {
        UserProfile user = userProfileRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return Optional.empty();
        }

        String platform = user.getRegistrationPlatformUsed();

        String token;
        if ("GOOGLE".equalsIgnoreCase(platform)) {
            token = authenticateWithGoogle(email);
        } else if ("FACEBOOK".equalsIgnoreCase(platform)) {
            token = authenticateWithFacebook(email);
        } else {
            throw new IllegalArgumentException("Unknown registration platform: " + platform);
        }

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

    private String authenticateWithGoogle(String email) {
        GoogleGateway googleGateway = new GoogleGateway();
        return googleGateway.authenticate(email);
    }

    private String authenticateWithFacebook(String email) {
        FacebookGateway facebookGateway = new FacebookGateway();
        return facebookGateway.authenticate(email);
    }
}
