package es.deusto.sd.strava.sd_strava.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class TrainingSession {
    private String title;
    private String sport;
    private float distance;
    private LocalDate startTime;
    private float duration;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserProfile userProfile;

    public TrainingSession() {}

    public TrainingSession(String title, String sport, float distance, LocalDate startTime, float duration, UserProfile userProfile) {
        this.title = title;
        this.sport = sport;
        this.distance = distance;
        this.startTime = startTime;
        this.duration = duration;
        this.userProfile = userProfile;
    }

    // Getters y Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
