package com.example.task_management.interfaces.controllers;

import com.example.task_management.application.dto.request.auth.LoginRequest;
import com.example.task_management.application.dto.request.auth.RegisterRequest;
import com.example.task_management.application.dto.response.ApiResponse;
import com.example.task_management.application.dto.response.auth.LoginResponse;
import com.example.task_management.application.dto.response.auth.RegisterResponse;
import com.example.task_management.application.usecases.auth.LoginUseCase;
import com.example.task_management.application.usecases.auth.RegisterUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    public AuthController(RegisterUseCase registerUseCase, LoginUseCase loginUseCase) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
    }

    // UC01 – Đăng ký
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse data = registerUseCase.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Đăng ký thành công", data));
    }

    // UC02 – Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse data = loginUseCase.login(request);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Đăng nhập thành công", data));
    }
}
