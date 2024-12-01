package es.deusto.sd.strava.sd_strava.dao;

import es.deusto.sd.strava.sd_strava.entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Integer> {
}
