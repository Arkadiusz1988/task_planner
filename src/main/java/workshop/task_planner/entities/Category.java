package workshop.task_planner.entities;

import lombok.Data;

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



}
