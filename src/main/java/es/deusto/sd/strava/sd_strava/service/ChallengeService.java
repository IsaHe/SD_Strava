// ChallengeService.java
package es.deusto.sd.strava.sd_strava.service;

import es.deusto.sd.strava.sd_strava.dao.ChallengeRepository;
import es.deusto.sd.strava.sd_strava.dao.UserProfileRepository;
import es.deusto.sd.strava.sd_strava.dto.ChallengeDTO;
import es.deusto.sd.strava.sd_strava.entity.Challenge;
import es.deusto.sd.strava.sd_strava.entity.TrainingSession;
import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserProfileService userProfileService;

    public ChallengeService(ChallengeRepository challengeRepository, UserProfileRepository userProfileRepository, UserProfileService userProfileService) {
        this.challengeRepository = challengeRepository;
        this.userProfileRepository = userProfileRepository;
        this.userProfileService = userProfileService;
    }

    public Challenge addChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public Optional<Challenge> getChallengeById(Integer id) {
        return challengeRepository.findById(id);
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public boolean getChallengeProgress(String email, Integer id) {
        UserProfile user = userProfileRepository.findByEmail(email).orElse(null);
        Challenge challenge = challengeRepository.findById(id).orElse(null);

        if (user == null || challenge == null) {
            return false;
        }

        user.setChallenges(userProfileService.getUserProfileWithChallenges(email).getChallenges());
        user.setTrainingSessions(userProfileService.getUserProfileWithTrainingSessions(email).getTrainingSessions());

        float score = challenge.getTarget();
        for (TrainingSession session : user.getTrainingSessions()) {

            if ((session.getStartTime().isAfter(challenge.getStartDate()) && session.getStartTime().isBefore(challenge.getEndDate()))
                    && session.getSport().equalsIgnoreCase(challenge.getSport())) {
                score -= session.getDistance();
            }
        }

        return score <= 0;
    }

    public ChallengeDTO  convertToDTO(Challenge challenge) {
        ChallengeDTO dto = new ChallengeDTO();
        dto.setChallengeID(challenge.getChallengeID());
        dto.setStartDate(challenge.getStartDate());
        dto.setEndDate(challenge.getEndDate());
        dto.setTargetType(challenge.getTargetType());
        dto.setTarget(challenge.getTarget());
        dto.setSport(challenge.getSport());
        return dto;
    }

    public Challenge convertToEntity(ChallengeDTO challengeDTO) {
        return new Challenge(
                challengeDTO.getStartDate(),
                challengeDTO.getEndDate(),
                challengeDTO.getTargetType(),
                challengeDTO.getTarget(),
                challengeDTO.getSport()
        );
    }
}