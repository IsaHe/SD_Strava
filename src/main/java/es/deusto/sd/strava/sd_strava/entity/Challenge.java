package es.deusto.sd.strava.sd_strava.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Challenge {
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

    public Challenge(LocalDate startDate, LocalDate endDate, String targetType, float target, String sport) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.targetType = targetType;
        this.target = target;
        this.sport = sport;

        users = new ArrayList<>();
    }

    // Getters y Setters
    public int getChallengeID() {
        return Math.toIntExact(id);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Challenge challenge = (Challenge) obj;
        return id.equals(challenge.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
