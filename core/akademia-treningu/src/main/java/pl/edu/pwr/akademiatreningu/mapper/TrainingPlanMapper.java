package pl.edu.pwr.akademiatreningu.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pwr.akademiatreningu.dto.TrainingExerciseDto;
import pl.edu.pwr.akademiatreningu.dto.TrainingPlanDto;
import pl.edu.pwr.akademiatreningu.model.Training;
import pl.edu.pwr.akademiatreningu.model.TrainingExercise;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TrainingPlanMapper {


    public List<TrainingPlanDto> mapToExerciseDtoList(List<Training> trainings) {
        return trainings.stream()
                .map(this::convertToTrainingPlanDto)
                .toList();
    }

    private TrainingPlanDto convertToTrainingPlanDto(Training training) {

        List<TrainingExerciseDto> trainingExerciseDtos = training.getTrainingExercises()
                .stream()
                .map(this::convertToTrainingExerciseDto)
                .toList();
        return TrainingPlanDto.builder()
                .trainingPlan(trainingExerciseDtos)
                .userId(training.getUser().getId())
                .dateOfCreate(training.getDateOfTraining())
                .build();
    }

    private TrainingExerciseDto convertToTrainingExerciseDto(TrainingExercise trainingExercise) {
        return TrainingExerciseDto.builder()
                .exerciseId(trainingExercise.getExercise().getId())
                .reps(trainingExercise.getReps())
                .sets(trainingExercise.getSets())
                .nameOfExercise(trainingExercise.getExercise().getName())
                .muscleGroup(trainingExercise.getExercise().getMuscleGroup())
                .build();
    }
}
