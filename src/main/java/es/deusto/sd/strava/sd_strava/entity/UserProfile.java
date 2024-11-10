package es.deusto.sd.strava.sd_strava.entity;

import java.time.LocalDate;
import java.util.List;

public class UserProfile {
    private String email;
    private String name;
    private LocalDate birthdate;
    private float weight;
    private float height;
    private float maxHeartRate;
    private float restHeartRate;
    private List<TrainingSession> trainingSessions;
    private List<Challenge> challenges;
    private String registrationPlatformUsed;

    public UserProfile(String email, String name, LocalDate birthdate, float weight, float height, float maxHeartRate, float restHeartRate, List<TrainingSession> trainingSessions, List<Challenge> challenges, String registrationPlatformUsed) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.weight = weight;
        this.height = height;
        this.maxHeartRate = maxHeartRate;
        this.restHeartRate = restHeartRate;
        this.trainingSessions = trainingSessions;
        this.challenges = challenges;
        this.registrationPlatformUsed = registrationPlatformUsed;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(float maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

    public float getRestHeartRate() {
        return restHeartRate;
    }

    public void setRestHeartRate(float restHeartRate) {
        this.restHeartRate = restHeartRate;
    }

    public String getRegistrationPlatformUsed() {
        return registrationPlatformUsed;
    }

    public void setRegistrationPlatformUsed(String registrationPlatformUsed) {
        this.registrationPlatformUsed = registrationPlatformUsed;
    }

    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(List<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }
}
