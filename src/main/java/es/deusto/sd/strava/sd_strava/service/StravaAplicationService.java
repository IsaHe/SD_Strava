package es.deusto.sd.strava.sd_strava.service;

import es.deusto.sd.strava.sd_strava.entity.Challenge;
import es.deusto.sd.strava.sd_strava.entity.TrainingSession;
import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StravaAplicationService {
    private final List<UserProfile> userProfiles = new ArrayList<>();

    public UserProfile registerUserProfile(UserProfile profile) {
        userProfiles.add(profile);
        return profile;
    }

    public UserProfile getUserProfileByEmail(String email) {
        for (UserProfile profile : userProfiles) {
            if (profile.getEmail().equals(email)) {
                return profile;
            }
        }
        return null;
    }

    public List<UserProfile> getAllUserProfiles() {
        return new ArrayList<>(userProfiles);
    }

    public Challenge addChallengeToUserProfile(String email, Challenge challenge) {
        UserProfile profile = getUserProfileByEmail(email);
        if (profile != null) {
            profile.getChallenges().add(challenge);
            return challenge;
        }
        return null;
    }

    public TrainingSession addTrainingSessionToUserProfile(String email, TrainingSession session) {
        UserProfile profile = getUserProfileByEmail(email);
        if (profile != null) {
            profile.getTrainingSessions().add(session);
            return session;
        }
        return null;
    }

    public List<Challenge> getChallengesByUserProfile(String email) {
        UserProfile profile = getUserProfileByEmail(email);
        if (profile != null) {
            return profile.getChallenges();
        }
        return null;
    }

    public List<TrainingSession> getTrainingSessionsByUserProfile(String email) {
        UserProfile profile = getUserProfileByEmail(email);
        if (profile != null) {
            return profile.getTrainingSessions();
        }
        return null;
    }

    public Challenge getChallengeById(String email, Long challengeId) {
        UserProfile profile = getUserProfileByEmail(email);
        if (profile == null) {
            for (Challenge challenge : profile.getChallenges()) {
                if (challenge.getChallengeID() == challengeId) {
                    return challenge;
                }
            }
        }
        return null;
    }

    public List<Challenge> getActiveChallengesByDate(String email, String currentDate) {
        UserProfile profile = getUserProfileByEmail(email);
        if (profile != null) {
            List<Challenge> activeChallenges = new ArrayList<>();
            for (Challenge challenge : profile.getChallenges()) {
                if (challenge.getStartDate().isBefore(LocalDate.parse(currentDate)) && challenge.getEndDate().isAfter(LocalDate.parse(currentDate))) {
                    activeChallenges.add(challenge);
                }
            }
            return activeChallenges;
        }
        return null;
    }

    public TrainingSession getTrainingSessionById(String email, Long sessionID) {
        UserProfile profile = getUserProfileByEmail(email);
        if (profile != null) {
            for (TrainingSession session : profile.getTrainingSessions()) {
                if (session.getTrainingSessionID() == sessionID) {
                    return session;
                }
            }
        }
        return null;
    }

    public List<TrainingSession> getRecentTrainingSessionsByUserProfile(String email) {
        UserProfile profile = getUserProfileByEmail(email);
        if (profile != null) {
            int limit = Math.min(profile.getTrainingSessions().size(), 5);
            return profile.getTrainingSessions().subList(profile.getTrainingSessions().size() - limit, profile.getTrainingSessions().size());
        }
        return null;
    }

    public List<TrainingSession> getTrainingSessionsWithinDatesByUserProfile(String email, LocalDate startDate, LocalDate endDate) {
        UserProfile profile = getUserProfileByEmail(email);
        if (profile != null) {
            List<TrainingSession> sessionsWithinDates = new ArrayList<>();
            for (TrainingSession session : profile.getTrainingSessions()) {
                if (!session.getStartTime().isBefore(startDate) && !session.getStartTime().isAfter(endDate)) {
                    sessionsWithinDates.add(session);
                }
            }
            return sessionsWithinDates;
        }
        return null;
    }
}