package es.deusto.sd.strava.sd_strava.dto;

public class TrainingSessionDTO {
    private int trainingSessionID;
    private String title;
    private String sport;
    private float distance;
    private String startTime;
    private float duration;

    public TrainingSessionDTO() {}

    public TrainingSessionDTO(int trainingSessionID, String title, String sport, float distance, String startTime, float duration) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
