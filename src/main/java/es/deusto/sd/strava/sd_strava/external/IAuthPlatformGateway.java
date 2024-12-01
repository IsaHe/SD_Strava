package es.deusto.sd.strava.sd_strava.external;

public interface IAuthPlatformGateway {
    public String authenticate(String email);
    public String addUser(String email, String name);
}
