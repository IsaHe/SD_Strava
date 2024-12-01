package es.deusto.sd.strava.sd_strava.Factory;

import es.deusto.sd.strava.sd_strava.external.FacebookGateway;
import es.deusto.sd.strava.sd_strava.external.GoogleGateway;
import es.deusto.sd.strava.sd_strava.external.IAuthPlatformGateway;
import org.springframework.stereotype.Component;

@Component
public class AuthGatewayFactory {
    private final GoogleGateway googleGateway;
    private final FacebookGateway facebookGateway;

    public AuthGatewayFactory(GoogleGateway googleGateway, FacebookGateway facebookGateway) {
        this.googleGateway = googleGateway;
        this.facebookGateway = facebookGateway;
    }

    public IAuthPlatformGateway getGateway(String platform) {
        if ("GOOGLE".equalsIgnoreCase(platform)) {
            return googleGateway;
        } else if ("FACEBOOK".equalsIgnoreCase(platform)) {
            return facebookGateway;
        }
        throw new IllegalArgumentException("Unsupported platform: " + platform);
    }
}
