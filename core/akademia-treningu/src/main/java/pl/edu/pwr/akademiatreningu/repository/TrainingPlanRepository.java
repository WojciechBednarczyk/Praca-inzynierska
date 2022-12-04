package pl.edu.pwr.akademiatreningu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.akademiatreningu.model.Training;

import java.util.List;

@Repository
public interface TrainingPlanRepository extends JpaRepository<Training, Integer> {

    List<Training> findAllByUserId(Integer userId);
}
