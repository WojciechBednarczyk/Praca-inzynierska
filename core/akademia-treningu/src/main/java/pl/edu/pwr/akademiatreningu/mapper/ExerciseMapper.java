package pl.edu.pwr.akademiatreningu.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pwr.akademiatreningu.dto.ExerciseDto;
import pl.edu.pwr.akademiatreningu.dto.TrainingExerciseDto;
import pl.edu.pwr.akademiatreningu.model.Exercise;
import pl.edu.pwr.akademiatreningu.model.TrainingExercise;
import pl.edu.pwr.akademiatreningu.repository.ExerciseRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExerciseMapper {

    private final ExerciseRepository exerciseRepository;

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

    public List<TrainingExercise> mapDtoToExercises(List<TrainingExerciseDto> trainingPlan) {
        return trainingPlan.stream()
                .map(this::convertToExercise)
                .toList();
    }

    private TrainingExercise convertToExercise(TrainingExerciseDto trainingExerciseDto) {
        return TrainingExercise.builder()
                .reps(trainingExerciseDto.getReps())
                .sets(trainingExerciseDto.getSets())
                .exercise(exerciseRepository.findById(trainingExerciseDto.getExerciseId()).get())
                .build();
    }
}
