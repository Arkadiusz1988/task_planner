package workshop.task_planner.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshop.task_planner.dto.CategoryDto;
import workshop.task_planner.entities.Category;
import workshop.task_planner.repositories.CategoryRepository;
import workshop.task_planner.service.BaseService;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements BaseService<CategoryDto,Long> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto){
        categoryRepository.save(categoryDto.toCategory());
        return categoryDto;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto,Long id){
        Category category = categoryRepository.getOne(id);
        category.setName(categoryDto.getName());
        // TO DO reszta setow
        categoryRepository.save(category);
        return category.toCategoryDto();
    }

    @Override
    public CategoryDto find(Long id) {
        Category category = categoryRepository.getOne(id);
        if (Objects.nonNull(category)) {
            return category.toCategoryDto();
        }
        return null;
    }

    @Override
    public Boolean remove(Long id) {
        Category category = categoryRepository.getOne(id);
        categoryRepository.delete(category);
        return true;
    }

    @Override
    public Collection<CategoryDto> getAll() {
        return categoryRepository
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(Category::toCategoryDto)
                .collect(Collectors.toList());
    }
}
