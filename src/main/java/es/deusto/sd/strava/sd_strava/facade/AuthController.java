package es.deusto.sd.strava.sd_strava.facade;

import es.deusto.sd.strava.sd_strava.dto.CredentialsDTO;
import es.deusto.sd.strava.sd_strava.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "API for user authentication")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "User login",
            description = "Allows a user to log in by providing email. Returns a token if successful.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: Login successful, returns a token"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid email, login failed"),
            }
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Parameter(name = "credentials", description = "User's credentials", required = true)
            @RequestBody CredentialsDTO credentials) {
        Optional<String> token = authService.login(credentials.getEmail());

        return token.map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    @Operation(
            summary = "User logout",
            description = "Allows a user to logout by providing the authorization token.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content: Logout successful"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid token, logout failed"),
            }
    )
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @Parameter(name = "token", description = "Authorization token", required = true, example = "Bearer 1924888a05c")
            @RequestBody String token) {
        Optional<Boolean> result = Optional.of(authService.logout(token));

        if (result.get()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
