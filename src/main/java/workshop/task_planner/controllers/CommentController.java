package workshop.task_planner.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.task_planner.dto.CommentDto;
import workshop.task_planner.entities.Comment;
import workshop.task_planner.repositories.CommentRepository;
import workshop.task_planner.service.imp.CommentServiceImp;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentServiceImp commentServiceImp;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentServiceImp commentServiceImp, CommentRepository commentRepository) {
        this.commentServiceImp = commentServiceImp;
        this.commentRepository = commentRepository;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public Result post(@RequestBody CommentDto commentDto) {
        return Result.ok(commentServiceImp.save(commentDto));
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Result get(@PathVariable Long id) {
        return Result.ok(commentServiceImp.find(id));
    }

    @GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
    public Result getAll() {
        return Result.ok(commentServiceImp.getAll());
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Result put(@RequestBody CommentDto commentDto,@PathVariable Long id) {
        return Result.ok(commentServiceImp.update(commentDto,id));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Result delete(@PathVariable Long id) {
        return Result.ok(commentServiceImp.remove(id));
    }

}
