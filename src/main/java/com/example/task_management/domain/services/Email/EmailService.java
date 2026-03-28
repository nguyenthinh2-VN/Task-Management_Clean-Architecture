package com.example.task_management.domain.services.Email;

public interface EmailService {

    void sendVerificationEmail(String to, String username, String token);

    void sendPasswordResetEmail(String to, String username, String token);
}
