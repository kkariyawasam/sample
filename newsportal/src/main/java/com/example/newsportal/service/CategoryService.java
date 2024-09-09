package com.example.newsportal.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.newsportal.dto.CategoryDto;
import com.example.newsportal.entity.Category;
import com.example.newsportal.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing categories.
 * This class handles the business logic for creating, retrieving categories.
 * It interacts with the {@link CategoryRepository} to fetch and modify data.
 * 
 * @author kalpani
 * @version 1.0
 * @since 2024-09-08
 */

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	  /**
     * Retrieves all categories from the database.
     *
     * @return A list of {@link CategoryDto} objects representing all categories.
     */
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> catDto = new ArrayList<>();

		for (Category category : categories) {
			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setId(category.getId());
			categoryDto.setName(category.getName());
			catDto.add(categoryDto);
		}
		return catDto;

	}

	  /**
     * Creates a new category.
     *
     * @param categoryDto The {@link CategoryDto} containing the details of the category to create.
     */
	public void saveCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
	    try {
	         categoryRepository.save(category);
	    } catch (DataIntegrityViolationException ex) {
	        throw new DataIntegrityViolationException("Category with name '" + category.getName() + "' already exists.");
	 

	    
	    }
	}
}
