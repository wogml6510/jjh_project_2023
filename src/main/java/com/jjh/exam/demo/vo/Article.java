package com.jjh.exam.demo.vo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	private int id;	
	private String regDate;
	private String updateDate;
	
	private String title;
	private String body;
}

