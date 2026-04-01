package com.example.task_management.application.usecases.auth;

import com.example.task_management.interfaces.dto.request.auth.LoginRequest;
import com.example.task_management.application.DTOUsecase.response.auth.AuthResult;


public interface LoginUseCase {
    AuthResult login(LoginRequest request);
}
