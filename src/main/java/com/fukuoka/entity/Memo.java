package com.fukuoka.entity;

import lombok.Data;

@Data
public class Memo {
  private Long id;
	
	private String content;
	
	private Integer userId;
}
