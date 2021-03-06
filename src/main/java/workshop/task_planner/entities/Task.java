package workshop.task_planner.entities;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import workshop.task_planner.dto.TaskDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @CreationTimestamp
    private Date creationDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "task" ,cascade = {CascadeType.ALL})
    private List<Comment> comments = new ArrayList<>();


    public Task(String name, String description, Date creationDate, User user, Category category, List<Comment> comments) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.user = user;
        this.category = category;
        this.comments = comments;
    }

    public Task() {
    }

    public TaskDto toTaskDto() {
        TaskDto taskDto = new TaskDto();
        taskDto.setName(name);
        taskDto.setDescription(description);
        return taskDto;
    }


}
