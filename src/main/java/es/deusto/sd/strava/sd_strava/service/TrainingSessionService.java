package es.deusto.sd.strava.sd_strava.service;

import es.deusto.sd.strava.sd_strava.entity.TrainingSession;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingSessionService {
    private final List<TrainingSession> trainingSessions = new ArrayList<>();

    public TrainingSession createTrainingSession(TrainingSession session) {
        trainingSessions.add(session);
        return session;
    }

    public List<TrainingSession> getRecentTrainingSessions() {
        int limit = Math.min(trainingSessions.size(), 5);
        return trainingSessions.subList(trainingSessions.size() - limit, trainingSessions.size());
    }

    public List<TrainingSession> getTrainingSessionsWithinDates(LocalDate startDate, LocalDate endDate) {
        List<TrainingSession> sessionsWithinDates = new ArrayList<>();
        for (TrainingSession session : trainingSessions) {
            if (!session.getStartTime().isBefore(startDate) && !session.getStartTime().isAfter(endDate)) {
                sessionsWithinDates.add(session);
            }
        }
        return sessionsWithinDates;
    }
}
