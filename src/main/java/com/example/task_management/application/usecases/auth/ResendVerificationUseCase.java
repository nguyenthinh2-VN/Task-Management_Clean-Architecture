package com.example.task_management.application.usecases.auth;

// UC05 – Gửi lại email xác thực
public interface ResendVerificationUseCase {
    void resendVerificationEmail(String email);
}
