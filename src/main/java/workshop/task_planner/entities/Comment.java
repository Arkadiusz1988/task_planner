package workshop.task_planner.entities;


import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Task task;

}
