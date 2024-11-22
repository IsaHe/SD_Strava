package es.deusto.sd.strava.sd_strava.entity;

import java.time.LocalDate;

public class TrainingSession {
    private int trainingSessionID;
    private String title;
    private String sport;
    private float distance;
    private LocalDate startTime;
    private float duration;

    public TrainingSession() {}

    public TrainingSession(int trainingSessionID, String title, String sport, float distance, LocalDate startTime, float duration) {
        this.trainingSessionID = trainingSessionID;
        this.title = title;
        this.sport = sport;
        this.distance = distance;
        this.startTime = startTime;
        this.duration = duration;
    }

    // Getters y Setters
    public int getTrainingSessionID() {
        return trainingSessionID;
    }

    public void setTrainingSessionID(int trainingSessionID) {
        this.trainingSessionID = trainingSessionID;
    }

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
}
