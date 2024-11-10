package es.deusto.sd.strava.sd_strava.facade;

import es.deusto.sd.strava.sd_strava.entity.Challenge;
import es.deusto.sd.strava.sd_strava.entity.TrainingSession;
import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import es.deusto.sd.strava.sd_strava.service.StravaAplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/user-profiles")
@Tag(name = "User Profile", description = "API to manage user profiles")
public class StravaApplicationController {

    @Autowired
    private StravaAplicationService stravaAplicationService;

    @PostMapping
    @Operation(summary = "Register a new user profile", description = "Allows registering a user profile with the provided information")
    public UserProfile registerUserProfile(@RequestBody UserProfile profile) {
        return stravaAplicationService.registerUserProfile(profile);
    }

    @GetMapping("/{email}")
    @Operation(summary = "Get user profile by email", description = "Retrieves the user profile based on the provided email")
    public UserProfile getUserProfileByEmail(
            @Parameter(description = "User's email")
            @PathVariable String email) {
        return stravaAplicationService.getUserProfileByEmail(email);
    }

    @GetMapping
    @Operation(summary = "Get all user profiles", description = "Gets the list of all user profiles")
    public List<UserProfile> getAllUserProfiles() {
        return stravaAplicationService.getAllUserProfiles();
    }

    @PostMapping("/{email}/challenges")
    @Operation(summary = "Add a challenge to a user profile", description = "Adds a challenge to the specified user profile")
    public Challenge addChallengeToUserProfile(
            @Parameter(description = "User's email")
            @PathVariable String email,
            @RequestBody Challenge challenge) {
        return stravaAplicationService.addChallengeToUserProfile(email, challenge);
    }

    @GetMapping("/{email}/challenges")
    @Operation(summary = "Get challenges by user profile", description = "Gets the list of challenges for the specified user profile")
    public List<Challenge> getChallengesByUserProfile(
            @Parameter(description = "User's email")
            @PathVariable String email) {
        return stravaAplicationService.getChallengesByUserProfile(email);
    }

    @GetMapping("/{email}/challenges/{challengeId}")
    @Operation(summary = "Get challenge by ID", description = "Retrieves the challenge with the specified ID")
    public Challenge getChallengeById(
            @Parameter(description = "User's email")
            @PathVariable String email,
            @Parameter(description = "Challenge ID")
            @PathVariable Long challengeId) {
        return stravaAplicationService.getChallengeById(email, challengeId);
    }

    @GetMapping("/{email}/challenges/active")
    @Operation(summary = "Get active challenges by user profile", description = "Gets the list of active challenges for the specified user profile in a specified date")
    public List<Challenge> getActiveChallengesByUserProfile(
            @Parameter(description = "User's email")
            @PathVariable String email,
            @Parameter(description = "Current date to filter active challenges")
            @RequestParam("currentDate") String currentDate) {
        return stravaAplicationService.getActiveChallengesByDate(email, currentDate);
    }



    @PostMapping("/{email}/training-sessions")
    @Operation(summary = "Add a training session to a user profile", description = "Adds a training session to the specified user profile")
    public TrainingSession addTrainingSessionToUserProfile(
            @Parameter(description = "User's email")
            @PathVariable String email,
            @RequestBody TrainingSession session) {
        return stravaAplicationService.addTrainingSessionToUserProfile(email, session);
    }

    @GetMapping("/{email}/training-sessions")
    @Operation(summary = "Get training sessions by user profile", description = "Gets the list of training sessions for the specified user profile")
    public List<TrainingSession> getTrainingSessionsByUserProfile(
            @Parameter(description = "User's email")
            @PathVariable String email) {
        return stravaAplicationService.getTrainingSessionsByUserProfile(email);
    }

    @GetMapping("/{email}/training-sessions/{sessionId}")
    @Operation(summary = "Get training session by ID", description = "Retrieves the training session with the specified ID")
    public TrainingSession getTrainingSessionById(
            @Parameter(description = "User's email")
            @PathVariable String email,
            @Parameter(description = "Training session ID")
            @PathVariable Long sessionId) {
        return stravaAplicationService.getTrainingSessionById(email, sessionId);
    }

    @GetMapping("/{email}/training-sessions/recent")
    @Operation(summary = "Get the 5 most recent training sessions by user profile", description = "Gets the 5 most recent training sessions for the specified user profile")
    public List<TrainingSession> getRecentTrainingSessionsByUserProfile(
            @Parameter(description = "User's email")
            @PathVariable String email) {
        return stravaAplicationService.getRecentTrainingSessionsByUserProfile(email);
    }

    @GetMapping("/{email}/training-sessions/by-dates")
    @Operation(summary = "Get training sessions within a date range by user profile", description = "Gets the training sessions within the specified date range for the specified user profile")
    public List<TrainingSession> getTrainingSessionsWithinDatesByUserProfile(
            @Parameter(description = "User's email")
            @PathVariable String email,
            @Parameter(description = "Start date of the range")
            @RequestParam("startDate") LocalDate startDate,
            @Parameter(description = "End date of the range")
            @RequestParam("endDate") LocalDate endDate) {
        return stravaAplicationService.getTrainingSessionsWithinDatesByUserProfile(email, startDate, endDate);
    }
}