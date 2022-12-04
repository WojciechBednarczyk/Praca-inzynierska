package pl.edu.pwr.akademiatreningu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.akademiatreningu.dto.ExerciseDto;
import pl.edu.pwr.akademiatreningu.service.ExerciseService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/get")
    @ResponseBody
    public List<ExerciseDto> getExercises() {
        return exerciseService.findAll();
    }


}
