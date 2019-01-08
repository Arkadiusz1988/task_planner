package workshop.task_planner.dto;


import lombok.Data;
import workshop.task_planner.entities.Task;


@Data
public class TaskDto {

    private String name;

    private String description;


    public Task toTask() {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        return task;
    }

}
