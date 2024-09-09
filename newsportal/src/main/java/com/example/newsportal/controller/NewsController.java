package com.example.newsportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.newsportal.dto.NewsDto;
import com.example.newsportal.service.NewsService;

import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Controller class for managing news-related API requests.
 * This class provides endpoints for creating, retrieving, and searching news items.
 * 
 * Endpoints include:
 * - Retrieving all news
 * - Retrieving a specific news item by ID
 * - Creating a new news item with categories
 * - Retrieving news by category ID
 * 
 * @author kalpani
 * @version 1.0
 * @since 2024-09-08
 */

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

	private final NewsService newsService;

	 /**
     * Retrieves all news items.
     *
     * @return A ResponseEntity containing a list of {@link NewsDto} objects representing all news items.
     */
	@GetMapping
	public ResponseEntity<List<NewsDto>> getAllNews() {
		return ResponseEntity.ok(newsService.getAllNews());
	}

    /**
     * Retrieves a specific news item by its ID.
     *
     * @param id The ID of the news item to retrieve.
     * @return A ResponseEntity containing a {@link NewsDto} object representing the requested news item.
     */
	@GetMapping("/{id}")
	public ResponseEntity<NewsDto> getNewsById(@PathVariable Long id) {
		return ResponseEntity.ok(newsService.getNewsById(id));
	}
	
    /**
     * Creates a new news item with associated categories.
     *
     * @param request The {@link NewsDto} containing the details of the news item to create.
     * @return A ResponseEntity containing a message indicating successful creation of the news item.
     */
	@PostMapping
	public ResponseEntity<String> createNewsItem(@RequestBody NewsDto request) {
		newsService.createNewsWithCategories(request.getTitle(), request.getContent(), request.getCategories());
		return ResponseEntity.status(HttpStatus.CREATED).body("News created successfully.");
	}

	 /**
     * Retrieves all news items associated with a specific category ID.
     *
     * @param categoryId The ID of the category to retrieve news for.
     * @return A ResponseEntity containing a list of {@link NewsDto} representing the news items associated with the category.
     */
	@GetMapping("category")
	public ResponseEntity<List<NewsDto>> getNewsByCategory(@RequestParam Long id) {
		return ResponseEntity.ok(newsService.getNewsByCategoryId(id));
	}
}
