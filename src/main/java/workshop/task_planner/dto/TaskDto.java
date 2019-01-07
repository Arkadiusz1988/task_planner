package workshop.task_planner.dto;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import workshop.task_planner.entities.Category;
import workshop.task_planner.entities.Task;
import workshop.task_planner.entities.User;

import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class TaskDto {

    private String name;

    private String description;

    @CreationTimestamp
    private Date creationDate;

    private boolean status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    public Task toTask() {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        return task;
    }

}
