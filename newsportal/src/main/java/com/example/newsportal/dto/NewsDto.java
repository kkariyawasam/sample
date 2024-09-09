package com.example.newsportal.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {

	private Long id;
	private String title;
	private String content;
	private List<Long> categories;
	private List<String> comments;

}
