package workshop.task_planner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import workshop.task_planner.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
