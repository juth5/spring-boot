package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.MemoMapper;

@CrossOrigin(origins = "*")
@RestController
public class SampleController {
	@Autowired
	MemoMapper memoMapper;
    // サンプルデータを返すエンドポイント
    @GetMapping("/sample")
    public SampleResponse sayHello() {
        return new SampleResponse("Hello", 123);
    }

    // JSONで返したいデータを保持するクラス
    public static class SampleResponse {
        private String message;
        private int value;

        // コンストラクタ
        public SampleResponse(String message, int value) {
            this.message = message;
            this.value = value;
        }

        // ゲッター
        public String getMessage() {
            return message;
        }

        public int getValue() {
            return value;
        }
    }
}