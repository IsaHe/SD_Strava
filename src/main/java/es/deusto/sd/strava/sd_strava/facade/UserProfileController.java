package es.deusto.sd.strava.sd_strava.facade;

import es.deusto.sd.strava.sd_strava.dto.UserProfileDTO;
import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import es.deusto.sd.strava.sd_strava.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user-profiles")
@Tag(name = "User Profile", description = "API to manage user profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Operation(
        summary = "Register a new user profile",
        description = "Allows registering a user profile with the provided information",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: User profile registered successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid input data")
        }
    )
    @PostMapping
    public ResponseEntity<UserProfileDTO> registerUserProfile(
            @Parameter(description = "User profile data", required = true)
            @RequestBody UserProfileDTO profileDTO) {
        UserProfile registeredProfile = userProfileService.registerUserProfile(userProfileService.convertToEntity(profileDTO));
        return new ResponseEntity<>(userProfileService.convertToDTO(registeredProfile), HttpStatus.OK);
    }

    @Operation(
        summary = "Get user profile by email",
        description = "Retrieves the user profile based on the provided email",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: User profile retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Not Found: User profile not found")
        }
    )
    @GetMapping("/{email}")
    public ResponseEntity<UserProfileDTO> getUserProfileByEmail(
            @Parameter(description = "User's email", required = true)
            @PathVariable String email) {
        UserProfile profile = userProfileService.getUserProfileByEmail(email);
        if (profile != null) {
            return new ResponseEntity<>(userProfileService.convertToDTO(profile), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
        summary = "Get all user profiles",
        description = "Gets the list of all user profiles",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: User profiles retrieved successfully")
        }
    )
    @GetMapping
    public ResponseEntity<List<UserProfileDTO>> getAllUserProfiles() {
        List<UserProfile> profiles = userProfileService.getAllUserProfiles();
        List<UserProfileDTO> dtos = new ArrayList<>();
        profiles.forEach(profile -> dtos.add(userProfileService.convertToDTO(profile)));
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}