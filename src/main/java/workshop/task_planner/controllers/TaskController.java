package workshop.task_planner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.task_planner.dto.TaskDto;
import workshop.task_planner.service.imp.TaskServiceImp;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServiceImp taskServiceImp;


    @Autowired
    public TaskController(TaskServiceImp taskServiceImp) {
        this.taskServiceImp = taskServiceImp;
    }


    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public Result post(@RequestBody TaskDto taskDto) {
        return Result.ok(taskServiceImp.save(taskDto));
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Result get(@PathVariable Long id) {
        return Result.ok(taskServiceImp.find(id));
    }

    @GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
    public Result getAll() {
        return Result.ok(taskServiceImp.getAll());
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Result put(@RequestBody TaskDto taskDto,@PathVariable Long id) {
        return Result.ok(taskServiceImp.update(taskDto,id));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Result delete(@PathVariable Long id) {
        return Result.ok(taskServiceImp.remove(id));
    }
}
