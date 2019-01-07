package workshop.task_planner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.task_planner.entities.Task;
import workshop.task_planner.repositories.TaskRepository;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public List<Task> getAllTask(){
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    @PostMapping("/post")
    public Task saveTask(@RequestBody Task tasks) {
        return  taskRepository.save(tasks);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskRepository.getOne(id);
    }// Diamond problem


    @PutMapping("/{id}")
    public Task editTask(@PathVariable Long id, @RequestBody Task task){
        Task tasks =  taskRepository.getOne(id);
        tasks.setName(task.getName());
        tasks.setName(task.getDescription());
        taskRepository.save(tasks);
        return tasks;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        taskRepository.delete(taskRepository.getOne(id));
    }
}
