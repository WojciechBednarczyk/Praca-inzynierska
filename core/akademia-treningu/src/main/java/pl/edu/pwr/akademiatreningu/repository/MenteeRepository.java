package pl.edu.pwr.akademiatreningu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.akademiatreningu.model.Mentee;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Integer> {

    Mentee findByUserId(Integer id);
}
