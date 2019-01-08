package workshop.task_planner.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshop.task_planner.dto.TaskDto;
import workshop.task_planner.entities.Task;
import workshop.task_planner.repositories.TaskRepository;
import workshop.task_planner.service.BaseService;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public TaskDto update(TaskDto taskDto, Long id){
        Task task = taskRepository.getOne(id);
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        // TO DO reszta setow
        taskRepository.save(task);
        return task.toTaskDto();
    }

    @Override
    public TaskDto find(Long id) {
        Task task = taskRepository.getOne(id);
        if (Objects.nonNull(task)) {
            return task.toTaskDto();
        }
        return null;
    }

    @Override
    public Boolean remove(Long id) {
        Task task = taskRepository.getOne(id);
        taskRepository.delete(task);
        return true;
    }

    @Override
    public Collection<TaskDto> getAll() {
        return taskRepository
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(Task::toTaskDto)
                .collect(Collectors.toList());
    }


}
