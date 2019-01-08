package workshop.task_planner.entities;

import lombok.Data;
import workshop.task_planner.dto.CategoryDto;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User user1;

    @OneToMany(mappedBy = "category" ,cascade = {CascadeType.ALL})
    private List<Task> tasks = new ArrayList<>();

    public Category(String name, User user1, List<Task> tasks) {
        this.name = name;
        this.user1 = user1;
        this.tasks = tasks;
    }

    public Category() {
    }

    public CategoryDto toCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(name);
        return categoryDto;
    }
}
