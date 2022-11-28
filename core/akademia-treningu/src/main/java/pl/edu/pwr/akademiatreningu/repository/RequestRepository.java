package pl.edu.pwr.akademiatreningu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.akademiatreningu.model.PersonalTrainerRequest;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<PersonalTrainerRequest, Integer> {

    Optional<PersonalTrainerRequest> findByPersonalTrainerIdAndMenteeId(Integer personalTrainerId, Integer menteeId);
}