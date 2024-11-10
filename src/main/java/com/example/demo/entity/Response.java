package com.example.demo.entity;

import lombok.Data;
import lombok.AllArgsConstructor;


@Data
@AllArgsConstructor // 全てのフィールドを引数に取るコンストラクタを生成

public class Response<T> { // <T>を追加してジェネリクス型を宣言
    private String status;
    private String message;
    private T data; // 汎用的なデータ型を指定
}
