package workshop.task_planner.dto;


import lombok.Data;
import workshop.task_planner.entities.Category;

@Data
public class CategoryDto {

    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }

    public CategoryDto() {
    }

    public Category toCategory() {
        Category category = new Category();
        category.setName(name);
        return category;
    }
}
