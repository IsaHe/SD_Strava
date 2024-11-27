package es.deusto.sd.strava.sd_strava.facade;

import es.deusto.sd.strava.sd_strava.dto.ChallengeDTO;
import es.deusto.sd.strava.sd_strava.entity.Challenge;
import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import es.deusto.sd.strava.sd_strava.service.AuthService;
import es.deusto.sd.strava.sd_strava.service.ChallengeService;
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
@RequestMapping("/api/challenges")
@Tag(name = "Challenge", description = "API to manage challenges")
public class ChallengeController {

    private final ChallengeService challengeService;
    private final AuthService authService;

    public ChallengeController(ChallengeService challengeService, AuthService autService) {
        this.challengeService = challengeService;
        this.authService = autService;
    }

    @Operation(
        summary = "Add a new challenge",
        description = "Allows adding a new challenge",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: Challenge added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid input data")
        }
    )
    @PostMapping("/{token}")
    public ResponseEntity<ChallengeDTO> addChallenge(
            @Parameter(description = "Authorization token", required = true)
            @PathVariable String token,
            @Parameter(description = "Challenge data", required = true)
            @RequestBody ChallengeDTO challengeDTO) {
        UserProfile user = authService.getUserByToken(token);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Challenge addedChallenge = challengeService.addChallenge(challengeService.convertToEntity(challengeDTO));
        user.getChallenges().add(addedChallenge);
        return new ResponseEntity<>(challengeService.convertToDTO(addedChallenge), HttpStatus.OK);
    }

    @Operation(
        summary = "Get challenge by ID",
        description = "Retrieves the challenge with the specified ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: Challenge retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Not Found: Challenge not found")
        }
    )
    @GetMapping("/{challengeId}")
    public ResponseEntity<ChallengeDTO> getChallengeById(
            @Parameter(description = "Challenge ID", required = true)
            @PathVariable Long challengeId) {
        Challenge challenge = challengeService.getChallengeById(challengeId);
        if (challenge != null) {
            return new ResponseEntity<>(challengeService.convertToDTO(challenge), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
        summary = "Get all challenges",
        description = "Gets the list of all challenges",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: Challenges retrieved successfully")
        }
    )
    @GetMapping
    public ResponseEntity<List<ChallengeDTO>> getAllChallenges() {
        List<Challenge> challenges = challengeService.getAllChallenges();
        List<ChallengeDTO> dtos = new ArrayList<>();
        challenges.forEach(challenge -> dtos.add(challengeService.convertToDTO(challenge)));
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Operation(
        summary = "Get challenges by user",
        description = "Gets the list of all challenges for the specified user",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: Challenges retrieved successfully")
        }
    )
    @GetMapping("/user/{token}")
    public ResponseEntity<List<ChallengeDTO>> getChallengesByUser(
            @Parameter(description = "Authorization token", required = true)
            @PathVariable String token) {
        UserProfile user = authService.getUserByToken(token);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<Challenge> challenges = user.getChallenges();
        List<ChallengeDTO> dtos = new ArrayList<>();
        challenges.forEach(challenge -> dtos.add(challengeService.convertToDTO(challenge)));
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}