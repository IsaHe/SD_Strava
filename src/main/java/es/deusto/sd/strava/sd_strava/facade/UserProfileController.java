package es.deusto.sd.strava.sd_strava.facade;

import es.deusto.sd.strava.sd_strava.entity.UserProfile;
import es.deusto.sd.strava.sd_strava.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/user-profiles")
@Tag(name = "User Profile", description = "API para gestionar perfiles de usuario")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping
    @Operation(summary = "Registrar un nuevo perfil de usuario", description = "Permite registrar un perfil de usuario con la información proporcionada")
    public UserProfile registerUserProfile(@RequestBody UserProfile profile) {
        return userProfileService.registerUserProfile(profile);
    }

    @GetMapping("/{email}")
    @Operation(summary = "Obtener perfil de usuario por email", description = "Recupera el perfil de usuario según el email proporcionado")
    public UserProfile getUserProfileByEmail(
            @Parameter(description = "Email del usuario")
            @PathVariable String email) {
        return userProfileService.getUserProfileByEmail(email);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los perfiles de usuario", description = "Obtiene la lista de todos los perfiles de usuario")
    public List<UserProfile> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }
}
