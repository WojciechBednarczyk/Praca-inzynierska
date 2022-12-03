package pl.edu.pwr.akademiatreningu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.akademiatreningu.model.TrainingExercise;

@Repository
public interface TrainingExercisesRepository extends JpaRepository<TrainingExercise, Integer> {

    void deleteAllByTrainingId(Integer trainingId);
}
