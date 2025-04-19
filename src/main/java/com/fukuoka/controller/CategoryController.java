package com.fukuoka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fukuoka.entity.Category;
import com.fukuoka.mapper.CategoryMapper;



@CrossOrigin(origins = "http://localhost:8081/")
@RestController
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;
    // サンプルデータを返すエンドポイント
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryMapper.findAll();
    }

    @PostMapping("/categories/post")
    public void postCategory(@RequestBody Category category) {
        categoryMapper.insert(category);
    }

}