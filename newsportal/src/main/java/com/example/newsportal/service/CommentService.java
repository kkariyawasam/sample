package com.example.newsportal.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.newsportal.dto.CommentDto;
import com.example.newsportal.entity.Comment;
import com.example.newsportal.entity.News;
import com.example.newsportal.exception.ResourceNotFoundException;
import com.example.newsportal.repository.CommentRepository;
import com.example.newsportal.repository.NewsRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing comments.
 * This class handles the business logic for creating comments for news items.
 * It interacts with the {@link CommentRepository} and {@link NewsRepository} to fetch and modify data.
 * 
 * @author Kalpani
 * @version 1.0
 * @since 2024-09-08
 */
@Service
@RequiredArgsConstructor
public class CommentService {

  
    private final CommentRepository commentRepository;
    
    private final NewsRepository newsRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    
    
    /**
     * Adds a new comment to a specific news item.
     *
     * @param commentDto The {@link CommentDto} containing the details of the comment to create.
     * @throws ResourceNotFoundException If the news item with the given ID is not found.
     */
    public void saveComment(CommentDto commentDto) {
    	
    	  News news = newsRepository.findById(commentDto.getNewsId())
                  .orElseThrow(() -> new ResourceNotFoundException("News not found with id " + commentDto.getNewsId()));

          Comment comment = new Comment();
          comment.setContent(commentDto.getComment());
          comment.setNews(news);

          commentRepository.save(comment);
     }

    	
}
    	


