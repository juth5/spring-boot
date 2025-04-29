package com.fukuoka.entity;

import lombok.Data;

@Data
public class ApiResponse<T> {
	private boolean success; // 成功か失敗か
	private String message;  // メッセージ
	private T data;


	// ✅ 成功用の static メソッドを追加！
	public static <T> ApiResponse<T> success(String message) {
		ApiResponse<T> response = new ApiResponse<>();
		response.success = true;
		response.message = message;
		response.data = null;
		return response;
	}

	// ✅ データも返したい場合用
	public static <T> ApiResponse<T> success(String message, T data) {
		ApiResponse<T> response = new ApiResponse<>();
		response.success = true;
		response.message = message;
		response.data = data;
		return response;
	}

	// ✅ 失敗用（エラー用）
	public static <T> ApiResponse<T> error(String message) {
		ApiResponse<T> response = new ApiResponse<>();
		response.success = false;
		response.message = message;
		response.data = null;
		return response;
	}
}

