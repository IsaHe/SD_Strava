package es.deusto.sd.strava.sd_strava.service;

import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileService {
    private final List<UserProfile> userProfiles = new ArrayList<>();

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
}
