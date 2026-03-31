package com.example.task_management.interfaces.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Bỏ qua các field null khi trả về JSON
public class ApiResponse<T> {
    
    @Builder.Default
    private String timestamp = LocalDateTime.now().toString();
    
    private int status;
    private String message;
    private T data;
    
    // Dành cho thông báo lỗi chi tiết (dành cho dev)
    private Object errors;

    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return ApiResponse.<T>builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(int status, String message, Object errors) {
        return ApiResponse.<T>builder()
                .status(status)
                .message(message)
                .errors(errors)
                .build();
    }
}
