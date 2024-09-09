package com.example.newsportal.service;

import com.example.newsportal.dto.NewsDto;
import com.example.newsportal.entity.Category;
import com.example.newsportal.entity.Comment;
import com.example.newsportal.entity.News;
import com.example.newsportal.exception.ResourceNotFoundException;
import com.example.newsportal.repository.CategoryRepository;
import com.example.newsportal.repository.NewsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing news items.
 * This class handles the business logic for creating, retrieving, and searching news items.
 * It interacts with the {@link NewsRepository} and {@link CategoryRepository} to fetch data.
 * 
 * @author kalpani
 * @version 1.0
 * @since 2024-09-08
 */
@Service
@RequiredArgsConstructor
public class NewsService {

	private final NewsRepository newsRepository;
	private final CategoryRepository categoryRepository;

	/**
     * Converts a {@link News} entity to a {@link NewsDto}.
     *
     * @param news The {@link News} entity to convert.
     * @return The converted {@link NewsDto} object.
     */
	private NewsDto convertToDto(News news) {
		NewsDto newsDto = new NewsDto();
		newsDto.setId(news.getId());
		newsDto.setTitle(news.getTitle());
		newsDto.setContent(news.getContent());

		// Categories
		List<Long> categoryIds = (news.getCategory() != null)
				? news.getCategory().stream().map(Category::getId).collect(Collectors.toList())
				: new ArrayList<>();
		newsDto.setCategories(categoryIds);

		// Comments
		List<String> comments = (news.getComments() != null)
				? news.getComments().stream().map(Comment::getContent).collect(Collectors.toList())
				: new ArrayList<>();
		newsDto.setComments(comments);

		return newsDto;
	}

	/**
     * Retrieves all news items from the database.
     *
     * @return A list of {@link NewsDto} objects representing all news items.
     */
	public List<NewsDto> getAllNews() {
		return newsRepository.findAll().stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	/**
     * Retrieves a specific news item by its ID.
     *
     * @param id The ID of the news item to retrieve.
     * @return A {@link NewsDto} object representing the requested news item.
     * @throws ResourceNotFoundException If the news item with the given ID is not found.
     */
	public NewsDto getNewsById(Long id) {
		News news = newsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("News not found"));

		return convertToDto(news);
	}

	/**
     * Creates a new news item with the specified title, content, and associated categories.
     *
     * @param title The title of the news article.
     * @param content The content of the news article.
     * @param categoryIds A set of category IDs associated with the news article.
     * @throws ResourceNotFoundException If any of the provided category IDs do not exist.
     */
	public void createNewsWithCategories(String title, String content, List<Long> categoryIds) {
		List<Category> categories = Collections.emptyList();
		if (categoryIds != null && !categoryIds.isEmpty()) {
			List<Category> fetchedCategories = new ArrayList<>(categoryRepository.findAllById(categoryIds));

			if (fetchedCategories.size() != categoryIds.size()) {
				throw new ResourceNotFoundException("Some categories with IDs " + categoryIds + " were not found");
			}
			categories = fetchedCategories;
		}

		News news = new News();
		news.setTitle(title);
		news.setContent(content);
		news.setCategory(categories);
		newsRepository.save(news);
	}

	/**
     * Retrieves all news items associated with a specific category ID.
     *
     * @param categoryId The ID of the category to retrieve news for.
     * @return A list of {@link NewsDto} representing the news items associated with the category.
     * @throws ResourceNotFoundException If the category with the given ID is not found.
     */
	public List<NewsDto> getNewsByCategoryId(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		return category.getNews().stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
}
