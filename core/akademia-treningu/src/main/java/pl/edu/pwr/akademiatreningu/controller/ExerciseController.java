package pl.edu.pwr.akademiatreningu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.akademiatreningu.dto.ExerciseDTO;
import pl.edu.pwr.akademiatreningu.model.Exercise;
import pl.edu.pwr.akademiatreningu.repository.ExerciseRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;

    @GetMapping("/get")
    @ResponseBody
    public List<ExerciseDTO> getExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream()
                .map(this::convertToDto)
                .toList();
    }

    private ExerciseDTO convertToDto(Exercise exercise) {
        return ExerciseDTO.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .description(exercise.getDescription())
                .muscleGroup(exercise.getMuscleGroup())
                .url(exercise.getUrl())
                .rating(exercise.getRating())
                .build();
    }
}
