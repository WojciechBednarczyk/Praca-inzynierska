package pl.edu.pwr.akademiatreningu.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwr.akademiatreningu.dto.ExerciseDTO;
import pl.edu.pwr.akademiatreningu.model.Exercise;

import java.util.List;

@Component
public class ExerciseMapper {

    public List<ExerciseDTO> mapExercisesToDto(List<Exercise> exercises) {
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
