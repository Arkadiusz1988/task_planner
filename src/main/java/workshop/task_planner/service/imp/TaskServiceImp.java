package workshop.task_planner.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshop.task_planner.dto.TaskDto;
import workshop.task_planner.entities.Task;
import workshop.task_planner.repositories.TaskRepository;
import workshop.task_planner.service.BaseService;

@Service
public class TaskServiceImp implements BaseService <TaskDto,Long> {

    private final TaskRepository taskRepository;


    @Autowired
    public TaskServiceImp(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto save(TaskDto taskDto){
        taskRepository.save(taskDto.toTask());
      return taskDto;
    }

    @Override
    public TaskDto update(TaskDto taskDto){
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        // TO DO reszta setow
        taskRepository.save(task);
        return task.toTaskDto();
    }


}
