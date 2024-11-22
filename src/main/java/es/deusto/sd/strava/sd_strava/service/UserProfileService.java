// UserProfileService.java
package es.deusto.sd.strava.sd_strava.service;

import es.deusto.sd.strava.sd_strava.dto.UserProfileDTO;
import es.deusto.sd.strava.sd_strava.entity.Challenge;
import es.deusto.sd.strava.sd_strava.entity.TrainingSession;
import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {
    private static final List<UserProfile> userProfiles = new ArrayList<>();

    public UserProfile registerUserProfile(UserProfile profile) {
        userProfiles.add(profile);
        return profile;
    }

    public UserProfile getUserProfileByEmail(String email) {
        for (UserProfile profile : userProfiles) {
            if (profile.getEmail().equals(email)) {
                return profile;
            }
        }
        return null;
    }

    public List<UserProfile> getAllUserProfiles() {
        return new ArrayList<>(userProfiles);
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
}