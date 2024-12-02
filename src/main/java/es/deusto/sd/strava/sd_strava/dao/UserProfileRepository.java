package es.deusto.sd.strava.sd_strava.dao;

import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    Optional<UserProfile> findByEmail(String email);

    @Query("SELECT u FROM UserProfile u LEFT JOIN FETCH u.trainingSessions WHERE u.email = :email")
    Optional<UserProfile> findByEmailWithTrainingSessions(@Param("email") String email);

    @Query("SELECT u FROM UserProfile u LEFT JOIN FETCH u.challenges WHERE u.email = :email")
    Optional<UserProfile> findByEmailWithChallenges(@Param("email") String email);
}
