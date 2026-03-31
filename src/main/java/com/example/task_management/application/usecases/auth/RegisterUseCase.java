package com.example.task_management.application.usecases.auth;

import com.example.task_management.interfaces.dto.request.auth.RegisterRequest;
import com.example.task_management.application.DTOUsecase.response.auth.RegisterResult;

// UC01 – Đăng ký
public interface RegisterUseCase {
    RegisterResult register(RegisterRequest request);
}
