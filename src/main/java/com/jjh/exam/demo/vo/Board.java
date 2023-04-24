package com.jjh.exam.demo.vo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int id;	
	private String regDate;
	private String updateDate;
	private String code;
	private String name;
	private boolean delStatus;
	private boolean delDate;
	
}

