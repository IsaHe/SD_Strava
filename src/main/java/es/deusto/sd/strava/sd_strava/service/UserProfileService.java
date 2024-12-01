package es.deusto.sd.strava.sd_strava.service;

import es.deusto.sd.strava.sd_strava.Factory.AuthGatewayFactory;
import es.deusto.sd.strava.sd_strava.dao.TrainingSessionRepository;
import es.deusto.sd.strava.sd_strava.dao.UserProfileRepository;
import es.deusto.sd.strava.sd_strava.dto.TrainingSessionDTO;
import es.deusto.sd.strava.sd_strava.dto.UserProfileDTO;
import es.deusto.sd.strava.sd_strava.entity.TrainingSession;
import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import es.deusto.sd.strava.sd_strava.external.IAuthPlatformGateway;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final TrainingSessionRepository trainingSessionRepository;
    private final AuthGatewayFactory authGatewayFactory;

    public UserProfileService(UserProfileRepository userProfileRepository, TrainingSessionRepository trainingSessionRepository, AuthGatewayFactory authGatewayFactory) {
        this.userProfileRepository = userProfileRepository;
        this.trainingSessionRepository = trainingSessionRepository;
        this.authGatewayFactory = authGatewayFactory;
    }

    // Gestión de usuarios
    public UserProfile registerUserProfile(UserProfile profile) {
        IAuthPlatformGateway gateway = authGatewayFactory.getGateway(profile.getRegistrationPlatformUsed());
        gateway.addUser(profile.getEmail(), profile.getName());
        return userProfileRepository.save(profile);
    }

    @Transactional
    public UserProfile getUserProfileByEmail(String email) {
        UserProfile user = userProfileRepository.findByEmail(email).orElse(null);
        if (user != null) {
            Hibernate.initialize(user.getTrainingSessions());
        }
        return user;    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public UserProfileDTO convertToDTO(UserProfile profile) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setEmail(profile.getEmail());
        dto.setName(profile.getName());
        dto.setBirthdate(profile.getBirthdate());
        dto.setWeight(profile.getWeight());
        dto.setHeight(profile.getHeight());
        dto.setMaxHeartRate(profile.getMaxHeartRate());
        dto.setRestHeartRate(profile.getRestHeartRate());
        dto.setRegistrationPlatformUsed(profile.getRegistrationPlatformUsed());
        return dto;
    }

    public UserProfile convertToEntity(UserProfileDTO profileDTO) {
        UserProfile profile = new UserProfile();
        profile.setEmail(profileDTO.getEmail());
        profile.setName(profileDTO.getName());
        profile.setBirthdate(profileDTO.getBirthdate());
        profile.setWeight(profileDTO.getWeight());
        profile.setHeight(profileDTO.getHeight());
        profile.setMaxHeartRate(profileDTO.getMaxHeartRate());
        profile.setRestHeartRate(profileDTO.getRestHeartRate());
        profile.setRegistrationPlatformUsed(profileDTO.getRegistrationPlatformUsed());
        return profile;
    }

    @Transactional
    public TrainingSession addTrainingSession(String userEmail, TrainingSessionDTO trainingSessionDTO) {
        UserProfile user = userProfileRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        TrainingSession session = convertToEntity(user, trainingSessionDTO);
        session.setUserProfile(user);
        TrainingSession savedSession = trainingSessionRepository.save(session);

        user.getTrainingSessions().add(savedSession);
        userProfileRepository.save(user);

        return savedSession;
    }

    public List<TrainingSessionDTO> getTrainingSessionsByUserEmail(String email) {
        UserProfile user = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return user.getTrainingSessions().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<TrainingSessionDTO> getTrainingSessionsByDateRange(String email, LocalDate start, LocalDate end) {
        UserProfile user = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return user.getTrainingSessions().stream()
                .filter(session -> !session.getStartTime().isBefore(start) && !session.getStartTime().isAfter(end))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Conversión DTO - Entidad
    public TrainingSession convertToEntity(UserProfile userProfile, TrainingSessionDTO trainingSessionDTO) {
        TrainingSession session = new TrainingSession();
        session.setTitle(trainingSessionDTO.getTitle());
        session.setSport(trainingSessionDTO.getSport());
        session.setDistance(trainingSessionDTO.getDistance());
        session.setStartTime(LocalDate.parse(trainingSessionDTO.getStartTime()));
        session.setDuration(trainingSessionDTO.getDuration());
        session.setUserProfile(userProfile);
        return session;
    }

    public TrainingSessionDTO convertToDTO(TrainingSession session) {
        TrainingSessionDTO dto = new TrainingSessionDTO();
        dto.setTitle(session.getTitle());
        dto.setSport(session.getSport());
        dto.setDistance(session.getDistance());
        dto.setStartTime(session.getStartTime().toString());
        dto.setDuration(session.getDuration());
        dto.setUserEmail(session.getUserProfile().getEmail());
        return dto;
    }
}
