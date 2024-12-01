package es.deusto.sd.strava.sd_strava.external;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FacebookGateway implements IAuthPlatformGateway {
    private final RestTemplate restTemplate = new RestTemplate();

    FacebookGateway() {}

    public String authenticate(String email) {
        String url = "http://localhost:8082/api/facebook/authenticate";
        try {
            return restTemplate.postForObject(url, email, String.class);
        } catch (Exception e) {
            return null;
        }    }

    @Override
    public String addUser(String email, String name) {
        return "";
    }
}
