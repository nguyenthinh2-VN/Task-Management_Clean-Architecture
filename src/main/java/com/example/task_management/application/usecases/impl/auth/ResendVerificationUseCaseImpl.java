package com.example.task_management.application.usecases.impl.auth;

import com.example.task_management.application.repositories.UserRepository;
import com.example.task_management.application.repositories.VerificationTokenRepository;
import com.example.task_management.application.usecases.auth.ResendVerificationUseCase;
import com.example.task_management.domain.entities.User;
import com.example.task_management.domain.entities.VerificationToken;
import com.example.task_management.domain.services.Email.EmailService;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResendVerificationUseCaseImpl implements ResendVerificationUseCase {

    private static final Logger log = LoggerFactory.getLogger(ResendVerificationUseCaseImpl.class);

    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;
    private final EmailService emailService;

    public ResendVerificationUseCaseImpl(UserRepository userRepository,
            VerificationTokenRepository tokenRepository,
            EmailService emailService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public void resendVerificationEmail(String email) {
        log.info("[ResendVerification] Bắt đầu - email={}", email);

        // 1. Tìm user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("[ResendVerification] User không tồn tại: email={}", email);
                    return new IllegalArgumentException("Email không tồn tại trong hệ thống");
                });

        // 2. Kiểm tra đã xác thực chưa
        if (user.isVerified()) {
            log.warn("[ResendVerification] User đã xác thực: userId={}", user.getId());
            throw new IllegalArgumentException("Tài khoản đã được xác thực.");
        }


        // 3. Kiểm tra cooldown (60 giây)
        Optional<VerificationToken> existingToken = tokenRepository.findByUserId(user.getId());
        if (existingToken.isPresent()) {
            LocalDateTime createdAt = existingToken.get().getExpiryDate().minusHours(24); // Token được tạo cách đây
            LocalDateTime now = LocalDateTime.now();
            if (createdAt.plusSeconds(60).isAfter(now)) {
                long secondsLeft = 60 - java.time.Duration.between(createdAt, now).getSeconds();
                log.warn("[ResendVerification] Request quá nhanh, còn {} giây", secondsLeft);
                throw new IllegalArgumentException("Vui lòng đợi " + secondsLeft + " giây trước khi gửi lại email.");
            }
            // Xóa token cũ
            log.debug("[ResendVerification] Xóa token cũ");
            tokenRepository.delete(existingToken.get());
        }

        // 4. Tạo token mới
        VerificationToken newToken = new VerificationToken(user.getId());
        tokenRepository.save(newToken);
        log.debug("[ResendVerification] Tạo token mới cho userId={}", user.getId());

        // 5. Gửi email
        emailService.sendVerificationEmail(user.getEmail(), user.getUsername(), newToken.getToken());
        log.info("[ResendVerification] Hoàn thành - đã gửi email cho userId={}", user.getId());
    }
}
