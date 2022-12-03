package pl.edu.pwr.akademiatreningu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.akademiatreningu.model.Training;

import java.util.Optional;

@Repository
public interface TrainingPlanRepository extends JpaRepository<Training, Integer> {

    Optional<Training> findByUserId(Integer userId);
}
