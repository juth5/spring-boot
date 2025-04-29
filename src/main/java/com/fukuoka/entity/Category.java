package com.fukuoka.entity;

import lombok.Data;

@Data
public class Category {
  private Long id;
	
	private String name;
	
	private String imageUrl;

	private boolean delFlg = false;
}
