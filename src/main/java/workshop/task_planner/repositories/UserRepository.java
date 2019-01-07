package workshop.task_planner.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import workshop.task_planner.dto.UserDto;
import workshop.task_planner.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  User findUserByUsername(String username);

  User findUserByEmail(String email);

  List<User> findAllById(Long id);



}
