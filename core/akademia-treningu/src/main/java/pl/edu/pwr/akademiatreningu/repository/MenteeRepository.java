package pl.edu.pwr.akademiatreningu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.akademiatreningu.model.Mentee;

public interface MenteeRepository extends JpaRepository<Mentee, Integer> {

    Mentee findByUserId(Integer id);
}
