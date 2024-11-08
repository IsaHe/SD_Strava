package es.deusto.sd.strava.sd_strava.service;

import es.deusto.sd.strava.sd_strava.entity.Challenge;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {
    private final List<Challenge> challenges = new ArrayList<>();

    public Challenge createChallenge(Challenge challenge) {
        challenges.add(challenge);
        return challenge;
    }

    public List<Challenge> getActiveChallenges(LocalDate currentDate) {
        List<Challenge> activeChallenges = new ArrayList<>();
        for (Challenge challenge : challenges) {
            if (challenge.getStartDate().isBefore(currentDate) && challenge.getEndDate().isAfter(currentDate)) {
                activeChallenges.add(challenge);
            }
        }
        return activeChallenges;
    }
}
