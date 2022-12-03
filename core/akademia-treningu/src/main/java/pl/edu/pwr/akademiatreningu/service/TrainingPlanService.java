package pl.edu.pwr.akademiatreningu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.akademiatreningu.dto.TrainingPlanDto;
import pl.edu.pwr.akademiatreningu.mapper.ExerciseMapper;
import pl.edu.pwr.akademiatreningu.mapper.TrainingPlanMapper;
import pl.edu.pwr.akademiatreningu.model.Training;
import pl.edu.pwr.akademiatreningu.model.TrainingExercise;
import pl.edu.pwr.akademiatreningu.repository.TrainingExercisesRepository;
import pl.edu.pwr.akademiatreningu.repository.TrainingPlanRepository;
import pl.edu.pwr.akademiatreningu.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingPlanService {

    private final TrainingPlanRepository trainingPlanRepository;

    private final TrainingExercisesRepository trainingExercisesRepository;

    private final UserRepository userRepository;

    private final ExerciseMapper exerciseMapper;

    private final TrainingPlanMapper trainingPlanMapper;

    public void saveTrainingPlan(TrainingPlanDto trainingPlanDto) {
        List<TrainingExercise> trainingExercises = exerciseMapper.mapDtoToExercises(trainingPlanDto.getTrainingPlan());
        Training trainingPlan = Training.builder()
                .user(userRepository.findById(trainingPlanDto.getUserId()).get())
                .trainingExercises(trainingExercises)
                .dateOfTraining(LocalDate.now())
                .build();
        trainingPlanRepository.save(trainingPlan);

        for (TrainingExercise exercise : trainingExercises) {
            exercise.setTraining(trainingPlan);
            trainingExercisesRepository.save(exercise);
        }
    }

    public List<TrainingPlanDto> getTrainingPlans(Integer userId) {
        List<Training> trainings = trainingPlanRepository.findAllByUserId(userId);
        return trainingPlanMapper.mapToExerciseDtoList(trainings);
    }
}
