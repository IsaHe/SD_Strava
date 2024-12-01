package es.deusto.sd.strava.sd_strava.dto;

public class ExternalUserDTO {
    private String email;
    private String name;

    public ExternalUserDTO() {
    }

    public ExternalUserDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
