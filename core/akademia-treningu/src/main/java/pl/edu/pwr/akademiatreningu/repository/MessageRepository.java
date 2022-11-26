package pl.edu.pwr.akademiatreningu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.akademiatreningu.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
