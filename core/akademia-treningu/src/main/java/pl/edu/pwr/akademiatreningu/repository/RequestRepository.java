package pl.edu.pwr.akademiatreningu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.akademiatreningu.model.PersonalTrainerRequest;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<PersonalTrainerRequest, Integer> {

    Optional<PersonalTrainerRequest> findByPersonalTrainerIdAndMenteeId(Integer personalTrainerId, Integer menteeId);

    List<PersonalTrainerRequest> findByPersonalTrainerId(Integer personalTrainerId);

    void deleteAllByMenteeId(Integer menteeId);

    void deleteByMenteeIdAndPersonalTrainerId(Integer menteeId, Integer personalTrainerId);
}
