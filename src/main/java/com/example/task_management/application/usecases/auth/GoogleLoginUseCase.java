package com.example.task_management.application.usecases.auth;

import com.example.task_management.interfaces.dto.request.auth.GoogleLoginRequest;
import com.example.task_management.application.DTOUsecase.response.auth.AuthResult;

public interface GoogleLoginUseCase {
    AuthResult loginWithGoogle(GoogleLoginRequest request);
}
