package com.example.task_management.application.usecases.auth;

import com.example.task_management.application.dto.request.auth.RegisterRequest;
import com.example.task_management.application.dto.response.auth.RegisterResponse;

// UC01 – Đăng ký
public interface RegisterUseCase {
    RegisterResponse register(RegisterRequest request);
}
