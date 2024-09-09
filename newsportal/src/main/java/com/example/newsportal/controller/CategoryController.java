package com.example.newsportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.newsportal.dto.CategoryDto;
import com.example.newsportal.service.CategoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Controller class for managing category-related API requests.
 * This class provides endpoints for creating, retrieving categories.
 * 
 * Endpoints include:
 * - Retrieving all categories
 * - Creating a new category
 * 
 * @author kalpani
 * @version 1.0
 * @since 2024-09-08
 */

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Retrieves all categories.
     *
     * @return A ResponseEntity containing a list of all {@link CategoryDto} objects.
     */
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    /**
     * Creates a new category.
     *
     * @param categoryDto The {@link CategoryDto} containing the details of the category to create.
     * @return A ResponseEntity containing a message indicating the successful creation of the category.
     */
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.saveCategory(categoryDto);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Category created successfully.");
    }
}
