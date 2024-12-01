package es.deusto.sd.strava.sd_strava.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Challenge {
    private int challengeID;
    private LocalDate startDate;
    private LocalDate endDate;
    private String targetType;
    private float target;
    private String sport;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "challenges")
    private List<UserProfile> users;

    public Challenge() {}

    public Challenge(int challengeID, LocalDate startDate, LocalDate endDate, String targetType, float target, String sport) {
        this.challengeID = challengeID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.targetType = targetType;
        this.target = target;
        this.sport = sport;
    }

    // Getters y Setters
    public int getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(int challengeID) {
        this.challengeID = challengeID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public float getTarget() {
        return target;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<UserProfile> getUsers() {
        return users;
    }

    public void setUsers(List<UserProfile> users) {
        this.users = users;
    }
}
