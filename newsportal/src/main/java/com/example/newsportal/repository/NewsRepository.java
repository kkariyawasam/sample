package com.example.newsportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.newsportal.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
	
}

