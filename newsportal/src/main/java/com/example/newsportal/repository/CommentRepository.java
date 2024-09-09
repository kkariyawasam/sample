package com.example.newsportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.newsportal.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}

