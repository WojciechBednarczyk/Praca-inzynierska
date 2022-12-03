package pl.edu.pwr.akademiatreningu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.akademiatreningu.dto.TrainingPlanDto;
import pl.edu.pwr.akademiatreningu.service.TrainingPlanService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class TrainingPlanController {

    private final TrainingPlanService trainingPlanService;

    @PostMapping("/training-plan")
    @PreAuthorize("hasRole('ROLE_PERSONAL_TRAINER')")
    public void saveTrainingPlan(@RequestBody TrainingPlanDto trainingPlanDto) {
        trainingPlanService.saveTrainingPlan(trainingPlanDto);
    }
}
