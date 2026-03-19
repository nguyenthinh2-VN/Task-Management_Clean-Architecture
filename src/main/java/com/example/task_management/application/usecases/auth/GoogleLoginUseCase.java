package com.example.task_management.application.usecases.auth;

import com.example.task_management.application.dto.request.auth.GoogleLoginRequest;
import com.example.task_management.application.dto.response.auth.LoginResponse;

public interface GoogleLoginUseCase {
    LoginResponse loginWithGoogle(GoogleLoginRequest request);
}
