package com.example.bookmanagment.Services;

import com.example.bookmanagment.Entities.feature.Category;
import com.example.bookmanagment.Entities.requests.CategoryRequest;
import com.example.bookmanagment.Exceptions.BadRequestAlertException;
import com.example.bookmanagment.Exceptions.ErrorCodes;
import com.example.bookmanagment.Repos.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void addCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        categoryRepository.save(category);
    }

    public void editCategory(CategoryRequest request, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BadRequestAlertException(ErrorCodes.NOT_FOUND_ERROR,"This Category does not exist"));
        category.setName(request.getName());
        categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public List<Category> getAllCategories() {
       return categoryRepository.findAll();
    }

    public Category getCategory(long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BadRequestAlertException(ErrorCodes.NOT_FOUND_ERROR,"This Category does not exist"));
    }
}
