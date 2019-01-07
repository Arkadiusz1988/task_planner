package workshop.task_planner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import workshop.task_planner.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
