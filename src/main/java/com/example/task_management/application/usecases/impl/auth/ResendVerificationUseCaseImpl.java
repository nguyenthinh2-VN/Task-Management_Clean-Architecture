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

        // 3. Tìm token mới nhất của user
        Optional<VerificationToken> existingTokenOpt = tokenRepository.findLatestByUserId(user.getId());
        VerificationToken token;

        if (existingTokenOpt.isPresent()) {
            VerificationToken existingToken = existingTokenOpt.get();

            // 3a. Kiểm tra token hết hạn
            if (!existingToken.isExpired() && !existingToken.isUsed()) {
                // 3b. Token chưa hết hạn -> check cooldown
                LocalDateTime createdAt = existingToken.getCreatedAt();
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime cooldownEnd = createdAt.plusSeconds(60);

                if (cooldownEnd.isAfter(now)) {
                    long secondsLeft = Math.max(0,
                            java.time.Duration.between(now, cooldownEnd).getSeconds());

                    log.warn("[ResendVerification] Request quá nhanh, còn {} giây", secondsLeft);
                    throw new IllegalArgumentException(
                            "Vui lòng đợi " + secondsLeft + " giây trước khi gửi lại email.");
                }
            }
            // Token hết hạn hoặc đã qua cooldown -> ghi đè token cũ
            log.debug("[ResendVerification] Ghi đè token cũ");
        
            existingToken.refresh();
            token = existingToken;
        } else {
            // Chưa có token -> tạo mới
            log.debug("[ResendVerification] Tạo token mới");
            token = new VerificationToken(user.getId());
        }

        // 4. Lưu token
        tokenRepository.save(token);
        log.debug("[ResendVerification] Lưu token cho userId={}", user.getId());

        // 5. Gửi email
        emailService.sendVerificationEmail(user.getEmail(), user.getUsername(), token.getToken());
        log.info("[ResendVerification] Hoàn thành - đã gửi email cho userId={}", user.getId());
    }
}
