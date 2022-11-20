package pl.edu.pwr.akademiatreningu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.akademiatreningu.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);

    Optional<User> findById(Integer id);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);
}
