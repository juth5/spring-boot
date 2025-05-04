package com.fukuoka.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fukuoka.entity.ApiResponse;
import com.fukuoka.entity.Category;
import com.fukuoka.mapper.CategoryMapper;
import com.google.api.gax.rpc.ApiException;



@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;
    // サンプルデータを返すエンドポイント
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryMapper.findAll();
    }

    @GetMapping("/categories/{id}")
    public Category getCategories(@PathVariable Long id) {
        return categoryMapper.findById(id);
    }

    //新規追加
    @PostMapping("/categories/post")
    public ResponseEntity<Map<String, String>> postCategory(@RequestBody Category category) {
        int inserted = categoryMapper.insert(category);

        Map<String, String> response = new HashMap<>();
        if (inserted == 1) {
            response.put("message", "新規登録に成功しました！");
            return ResponseEntity.ok(response);
        }
        else {
            response.put("message", "新規登録に失敗しました。");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Map<String, String>> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        categoryMapper.update(category);
        Map<String, String> response = new HashMap<>();
        response.put("message", "更新に成功しました！");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/categories/{id}/delete")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        int updated = categoryMapper.logicalDeleteById(id);

        if (updated != 1) {
            // throw new ApiException(HttpStatus.NOT_FOUND, "対象のカテゴリーが存在しません。");
        }

        return ResponseEntity.ok(ApiResponse.success("削除が成功しました。"));
    }
}