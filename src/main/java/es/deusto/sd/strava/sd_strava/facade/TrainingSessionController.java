package es.deusto.sd.strava.sd_strava.facade;

import es.deusto.sd.strava.sd_strava.entity.TrainingSession;
import es.deusto.sd.strava.sd_strava.service.TrainingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/training-sessions")
@Tag(name = "Training Session", description = "API para gestionar sesiones de entrenamiento")
public class TrainingSessionController {

    @Autowired
    private TrainingSessionService trainingSessionService;

    @PostMapping
    @Operation(summary = "Crear una nueva sesión de entrenamiento", description = "Permite crear manualmente una sesión de entrenamiento")
    public TrainingSession createTrainingSession(@RequestBody TrainingSession session) {
        return trainingSessionService.createTrainingSession(session);
    }

    @GetMapping("/recent")
    @Operation(summary = "Obtener las 5 sesiones de entrenamiento más recientes", description = "Devuelve las 5 sesiones más recientes")
    public List<TrainingSession> getRecentTrainingSessions() {
        return trainingSessionService.getRecentTrainingSessions();
    }

    @GetMapping("/by-dates")
    @Operation(summary = "Obtener sesiones de entrenamiento dentro de un rango de fechas", description = "Devuelve las sesiones de entrenamiento en el rango de fechas especificado")
    public List<TrainingSession> getTrainingSessionsWithinDates(
            @Parameter(description = "Fecha de inicio del rango") @RequestParam("startDate") LocalDate startDate,
            @Parameter(description = "Fecha de fin del rango") @RequestParam("endDate") LocalDate endDate) {
        return trainingSessionService.getTrainingSessionsWithinDates(startDate, endDate);
    }
}
