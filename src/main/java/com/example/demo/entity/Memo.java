package com.example.demo.entity;

import lombok.Data;

@Data
public class Memo {
  private Long id;
	
	private String content;
	
	private Integer userId;
}
