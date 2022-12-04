package pl.edu.pwr.akademiatreningu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.akademiatreningu.model.Opinion;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Integer> {

    Optional<Opinion> findByPersonalTrainerIdAndMenteeId(Integer personalTrainerId, Integer menteeId);

    Integer countAllByPersonalTrainerId(Integer personalTrainerId);

    List<Opinion> findAllByPersonalTrainerId(Integer personalTrainerId);
}
