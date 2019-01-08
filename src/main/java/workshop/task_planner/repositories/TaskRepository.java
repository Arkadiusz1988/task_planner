package workshop.task_planner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import workshop.task_planner.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
