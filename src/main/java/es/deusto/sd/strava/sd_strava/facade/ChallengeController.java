package es.deusto.sd.strava.sd_strava.facade;

import es.deusto.sd.strava.sd_strava.entity.Challenge;
import es.deusto.sd.strava.sd_strava.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/challenges")
@Tag(name = "Challenge", description = "API para gestionar desafíos")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @PostMapping
    @Operation(summary = "Crear un nuevo desafío", description = "Permite crear un desafío con los datos especificados")
    public Challenge createChallenge(@RequestBody Challenge challenge) {
        return challengeService.createChallenge(challenge);
    }

    @GetMapping("/active")
    @Operation(summary = "Obtener desafíos activos", description = "Obtiene la lista de desafíos activos según la fecha actual")
    public List<Challenge> getActiveChallenges(
            @Parameter(description = "Fecha actual para filtrar desafíos activos")
            @RequestParam("currentDate") LocalDate currentDate) {
        return challengeService.getActiveChallenges(currentDate);
    }
}
