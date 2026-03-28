package com.example.task_management.application.usecases.auth;

// UC04 – Xác thực email
public interface VerifyEmailUseCase {
    void verifyEmail(String token);
}
