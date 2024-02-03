package com.example.bookmanagment.Controllers;

import com.example.bookmanagment.Entities.feature.Category;
import com.example.bookmanagment.Entities.requests.CategoryRequest;
import com.example.bookmanagment.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void addBook(@RequestBody CategoryRequest request) {
        categoryService.addCategory(request);
    }

    @PutMapping("{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void editBook(@RequestBody CategoryRequest request, @PathVariable Long categoryId) {
        categoryService.editCategory(request, categoryId);
    }

    @DeleteMapping("{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("{categoryId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Category getCategory(@PathVariable long categoryId) {
        return categoryService.getCategory(categoryId);
    }

}
