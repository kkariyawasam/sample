package com.example.newsportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String content;

	@ManyToMany
	@JoinTable(name = "news_category", 
	joinColumns = @JoinColumn(name = "news_id"),
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> category;

	@OneToMany(mappedBy = "news")
	private List<Comment> comments;

}
