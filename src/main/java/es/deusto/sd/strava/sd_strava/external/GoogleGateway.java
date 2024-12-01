package es.deusto.sd.strava.sd_strava.external;

import org.springframework.web.client.RestTemplate;

public class GoogleGateway implements IAuthPlatformGateway {
    private final RestTemplate restTemplate = new RestTemplate();

    public String authenticate(String email) {
        String url = "http://localhost:8081/api/google/authenticate";
        try {
            return restTemplate.postForObject(url, email, String.class);
        } catch (Exception e) {
            return null;
        }
    }
}
