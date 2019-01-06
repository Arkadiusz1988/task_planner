package workshop.task_planner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import workshop.task_planner.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  User findUserByUsername(String username);

  User findUserByEmail(String email);

  List<User> findAllById(Long id);
}
