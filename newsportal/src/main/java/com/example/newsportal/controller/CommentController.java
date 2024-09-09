package com.example.newsportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.newsportal.dto.CommentDto;

import com.example.newsportal.service.CommentService;

import lombok.RequiredArgsConstructor;

/**
 * Controller class for managing comment-related API requests.
 * This class provides endpoints for creating comments for news items.
 * 
 * Endpoints include:
 * - Retrieving comments for a specific news item
 * - Creating a comment for a specific news item
 * 
 * @author kalpani
 * @version 1.0
 * @since 2024-09-08
 */
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Creates a new comment for a specific news item.
     *
     * @param commentDto The {@link CommentDto} containing the details of the comment to create.
     * @return A ResponseEntity containing a message indicating the successful creation of the comment.
     */
    @PutMapping
    public ResponseEntity<String> createComment(@RequestBody CommentDto commentDto) {
        commentService.saveComment(commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment updated successfully.");
    }
}

