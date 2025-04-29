package com.fukuoka.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fukuoka.entity.ApiResponse;
import com.google.api.gax.rpc.ApiException;

// ✅ @RestControllerAdvice と
// ✅ @ExceptionHandler(Exception.class)

// 👉 この2つは両方セットで必要です！！
// public class GlobalExceptionHandler {
//       // 自作のビジネスエラー（ApiException）だけキャッチ
//     @ExceptionHandler(ApiException.class)
//     public ResponseEntity<ApiResponse<Void>> handleApiException(ApiException e) {
//         return ResponseEntity
//                 .status(e.getStatus())
//                 .body(ApiResponse.error(e.getMessage()));
//     }
// }
