package workshop.task_planner.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.task_planner.dto.CategoryDto;
import workshop.task_planner.service.imp.CategoryServiceImp;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryServiceImp categoryServiceImp;

    @Autowired
    public CategoryController(CategoryServiceImp categoryServiceImp) {
        this.categoryServiceImp = categoryServiceImp;
    }


    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public Result post(@RequestBody CategoryDto categoryDto) {
        return Result.ok(categoryServiceImp.save(categoryDto));
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Result get(@PathVariable Long id) {
        return Result.ok(categoryServiceImp.find(id));
    }

    @GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
    public Result getAll() {
        return Result.ok(categoryServiceImp.getAll());
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Result put(@RequestBody CategoryDto categoryDto,@PathVariable Long id) {
        return Result.ok(categoryServiceImp.update(categoryDto,id));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Result delete(@PathVariable Long id) {
        return Result.ok(categoryServiceImp.remove(id));
    }
}
