package pl.edu.pwr.akademiatreningu.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwr.akademiatreningu.dto.ExerciseDto;
import pl.edu.pwr.akademiatreningu.model.Exercise;

import java.util.List;

@Component
public class ExerciseMapper {

    public List<ExerciseDto> mapExercisesToDto(List<Exercise> exercises) {
        return exercises.stream()
                .map(this::convertToDto)
                .toList();
    }

    private ExerciseDto convertToDto(Exercise exercise) {
        return ExerciseDto.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .description(exercise.getDescription())
                .muscleGroup(exercise.getMuscleGroup())
                .url(exercise.getUrl())
                .rating(exercise.getRating())
                .build();
    }
}
