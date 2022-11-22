package pl.edu.pwr.akademiatreningu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.akademiatreningu.dto.ExerciseDto;
import pl.edu.pwr.akademiatreningu.mapper.ExerciseMapper;
import pl.edu.pwr.akademiatreningu.model.Exercise;
import pl.edu.pwr.akademiatreningu.repository.ExerciseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final ExerciseMapper exerciseMapper;

    public List<ExerciseDto> findAll() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exerciseMapper.mapExercisesToDto(exercises);
    }
}
