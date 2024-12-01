package es.deusto.sd.strava.sd_strava.external;

import es.deusto.sd.strava.sd_strava.dto.ExternalUserDTO;
import es.deusto.sd.strava.sd_strava.dto.UserProfileDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.deusto.sd.strava.sd_strava.entity.UserProfile;

@Component
public class GoogleGateway implements IAuthPlatformGateway {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String GOOGLE_SERVER_AUTH_URL = "http://localhost:8081/api/google/authenticate";
    private static final String GOOGLE_SERVER_ADD_USER_URL = "http://localhost:8081/api/google/add-user";

    GoogleGateway() {}

    @Override
    public String authenticate(String email) {
        try {
            return restTemplate.postForObject(GOOGLE_SERVER_AUTH_URL, email, String.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String addUser(String email, String name) {
        try {
            return restTemplate.postForObject(GOOGLE_SERVER_ADD_USER_URL, new ExternalUserDTO(email, name), String.class);
        } catch (Exception e) {
            return null;
        }
    }
}
