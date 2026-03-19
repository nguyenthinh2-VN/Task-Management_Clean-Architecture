package com.example.task_management.application.usecases.auth;

import com.example.task_management.application.dto.request.auth.LoginRequest;
import com.example.task_management.application.dto.response.auth.LoginResponse;

// UC02 – Đăng nhập
public interface LoginUseCase {
    LoginResponse login(LoginRequest request);
}
