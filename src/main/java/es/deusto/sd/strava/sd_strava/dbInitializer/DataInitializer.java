package es.deusto.sd.strava.sd_strava.dbInitializer;

import es.deusto.sd.strava.sd_strava.dao.ChallengeRepository;
import es.deusto.sd.strava.sd_strava.dao.TrainingSessionRepository;
import es.deusto.sd.strava.sd_strava.dao.UserProfileRepository;
import es.deusto.sd.strava.sd_strava.entity.Challenge;
import es.deusto.sd.strava.sd_strava.entity.TrainingSession;
import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserProfileRepository userRepository;
    private final ChallengeRepository challengeRepository;

    public DataInitializer(UserProfileRepository userRepository, ChallengeRepository challengeRepository) {
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
    }

    @Override
    public void run(String... args) {
        UserProfile user1 = new UserProfile("user1@gmail.com", "User One", LocalDate.of(2003, 7, 29), 90, 180, 100, 50, "google");
        UserProfile user2 = new UserProfile("user2@gmail.com", "User Two", LocalDate.of(2000, 5, 15), 70, 170, 90, 40, "google");
        UserProfile user3 = new UserProfile("user3@facebook.com", "User Three", LocalDate.of(1999, 3, 12), 80, 175, 95, 45, "facebook");
        UserProfile user4 = new UserProfile("user4@facebook.com", "User Four", LocalDate.of(1998, 1, 1), 75, 160, 85, 35, "facebook");

        TrainingSession trainingSession1 = new TrainingSession("Session 1", "Running", 10, LocalDate.of(2021, 1, 1), 60, user1);
        TrainingSession trainingSession2 = new TrainingSession("Session 2", "Cycling", 20, LocalDate.of(2021, 2, 1), 120, user1);
        TrainingSession trainingSession3 = new TrainingSession("Session 3", "Running", 15, LocalDate.of(2021, 3, 1), 90, user1);

        user1.getTrainingSessions().add(trainingSession1);
        user1.getTrainingSessions().add(trainingSession2);
        user1.getTrainingSessions().add(trainingSession3);

        TrainingSession trainingSession4 = new TrainingSession("Session 4", "Running", 10, LocalDate.of(2021, 1, 1), 60, user2);
        TrainingSession trainingSession5 = new TrainingSession("Session 5", "Cycling", 20, LocalDate.of(2021, 2, 1), 120, user2);
        TrainingSession trainingSession6 = new TrainingSession("Session 6", "Running", 15, LocalDate.of(2021, 3, 1), 90, user2);

        user2.getTrainingSessions().add(trainingSession4);
        user2.getTrainingSessions().add(trainingSession5);
        user2.getTrainingSessions().add(trainingSession6);

        TrainingSession trainingSession7 = new TrainingSession("Session 7", "Running", 150, LocalDate.of(2024, 4, 1), 60, user3);
        TrainingSession trainingSession8 = new TrainingSession("Session 8", "Cycling", 20, LocalDate.of(2021, 2, 1), 120, user3);
        TrainingSession trainingSession9 = new TrainingSession("Session 9", "Running", 15, LocalDate.of(2024, 5, 1), 90, user3);

        user3.getTrainingSessions().add(trainingSession7);
        user3.getTrainingSessions().add(trainingSession8);
        user3.getTrainingSessions().add(trainingSession9);

        TrainingSession trainingSession10 = new TrainingSession("Session 10", "Running", 10, LocalDate.of(2021, 1, 1), 60, user4);
        TrainingSession trainingSession11 = new TrainingSession("Session 11", "Cycling", 20, LocalDate.of(2021, 2, 1), 120, user4);
        TrainingSession trainingSession12 = new TrainingSession("Session 12", "Running", 15, LocalDate.of(2021, 3, 1), 90, user4);

        user4.getTrainingSessions().add(trainingSession10);
        user4.getTrainingSessions().add(trainingSession11);
        user4.getTrainingSessions().add(trainingSession12);

        Challenge challenge1 = new Challenge(LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 31), "Distance", 100, "Running");
        Challenge challenge2 = new Challenge(LocalDate.of(2024, 2, 1), LocalDate.of(2025, 2, 28), "Time", 60, "Cycling");
        Challenge challenge3 = new Challenge(LocalDate.of(2024, 3, 1), LocalDate.of(2025, 3, 31), "Distance", 150, "Running");

        user1.getChallenges().add(challenge1);
        challenge1.getUsers().add(user1);

        user2.getChallenges().add(challenge2);
        challenge2.getUsers().add(user2);

        user3.getChallenges().add(challenge3);
        challenge3.getUsers().add(user3);

        challengeRepository.save(challenge1);
        challengeRepository.save(challenge2);
        challengeRepository.save(challenge3);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
    }
}
