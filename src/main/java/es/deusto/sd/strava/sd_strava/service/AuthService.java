package es.deusto.sd.strava.sd_strava.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import es.deusto.sd.strava.sd_strava.entity.Challenge;
import org.springframework.stereotype.Service;

import es.deusto.sd.strava.sd_strava.entity.UserProfile;

@Service
public class AuthService {

    private static final Map<String, UserProfile> tokenStore = new HashMap<>();
    private final UserProfileService userStore = new UserProfileService();

    public Optional<String> login(String email) {
        UserProfile user = userStore.getUserProfileByEmail(email);

        if (user != null) {
            String token = generateToken();
            tokenStore.put(token, user);
            user.setToken(token);

            return Optional.of(token);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Boolean> logout(String token) {
        if (tokenStore.containsKey(token)) {
            tokenStore.remove(token);
            UserProfile user = userStore.getUserProfileByEmail(tokenStore.get(token).getEmail());
            user.setToken(null);

            return Optional.of(true);
        } else {
            return Optional.empty();
        }
    }

    public UserProfile getUserByToken(String token) {
        return tokenStore.get(token);
    }

    private static synchronized String generateToken() {
        return Long.toHexString(System.currentTimeMillis());
    }

}