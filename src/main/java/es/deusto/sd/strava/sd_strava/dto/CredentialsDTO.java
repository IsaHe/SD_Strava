package es.deusto.sd.strava.sd_strava.dto;

public class CredentialsDTO {
    private String email;

    public CredentialsDTO() {
    }

    public CredentialsDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}