// ChallengeService.java
package es.deusto.sd.strava.sd_strava.service;

import es.deusto.sd.strava.sd_strava.dao.ChallengeRepository;
import es.deusto.sd.strava.sd_strava.dto.ChallengeDTO;
import es.deusto.sd.strava.sd_strava.entity.Challenge;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
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