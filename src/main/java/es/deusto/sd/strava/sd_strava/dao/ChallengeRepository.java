package es.deusto.sd.strava.sd_strava.dao;

import es.deusto.sd.strava.sd_strava.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {
}
