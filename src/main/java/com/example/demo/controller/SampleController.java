package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class SampleController {

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