package com.example.task_management.interfaces.controllers;

import com.example.task_management.interfaces.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class ConnectionTestController {

    @GetMapping("/connection")
    public ResponseEntity<ApiResponse<Map<String, Object>>> testConnection() {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "connected");
        data.put("timestamp", LocalDateTime.now().toString());
        data.put("application", "Task Management System");
        data.put("version", "1.0.0");
        
        return ResponseEntity.ok(ApiResponse.success(
            HttpStatus.OK.value(), 
            "Kết nối thành công!", 
            data
        ));
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Map<String, Object>>> healthCheck() {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "healthy");
        data.put("timestamp", LocalDateTime.now().toString());
        data.put("uptime", System.currentTimeMillis());
        
        return ResponseEntity.ok(ApiResponse.success(
            HttpStatus.OK.value(), 
            "Ứng dụng đang hoạt động bình thường", 
            data
        ));
    }
}
