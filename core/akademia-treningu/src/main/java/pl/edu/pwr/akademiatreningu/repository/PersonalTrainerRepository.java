package pl.edu.pwr.akademiatreningu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.akademiatreningu.model.PersonalTrainer;

@Repository
public interface PersonalTrainerRepository extends JpaRepository<PersonalTrainer, Integer> {

    PersonalTrainer findByUserId(Integer id);
}
