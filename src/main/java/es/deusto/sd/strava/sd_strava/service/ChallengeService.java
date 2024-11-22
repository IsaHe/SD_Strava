// ChallengeService.java
package es.deusto.sd.strava.sd_strava.service;

import es.deusto.sd.strava.sd_strava.dto.ChallengeDTO;
import es.deusto.sd.strava.sd_strava.entity.Challenge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {
    private final List<Challenge> challenges = new ArrayList<>();

    public Challenge addChallenge(Challenge challenge) {
        challenges.add(challenge);
        return challenge;
    }

    public Challenge getChallengeById(Long challengeId) {
        for (Challenge challenge : challenges) {
            if (challenge.getChallengeID() == challengeId) {
                return challenge;
            }
        }
        return null;
    }

    public List<Challenge> getAllChallenges() {
        return new ArrayList<>(challenges);
    }

    public ChallengeDTO convertToDTO(Challenge challenge) {
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
                challengeDTO.getChallengeID(),
                challengeDTO.getStartDate(),
                challengeDTO.getEndDate(),
                challengeDTO.getTargetType(),
                challengeDTO.getTarget(),
                challengeDTO.getSport()
        );
    }
}