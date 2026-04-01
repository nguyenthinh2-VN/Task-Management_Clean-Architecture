package com.example.task_management.application.usecases.impl.auth;

import com.example.task_management.application.repositories.UserRepository;
import com.example.task_management.application.repositories.VerificationTokenRepository;
import com.example.task_management.application.usecases.auth.VerifyEmailUseCase;
import com.example.task_management.domain.entities.User;
import com.example.task_management.domain.entities.VerificationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VerifyEmailUseCaseImpl implements VerifyEmailUseCase {

    private static final Logger log = LoggerFactory.getLogger(VerifyEmailUseCaseImpl.class);

    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public VerifyEmailUseCaseImpl(VerificationTokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void verifyEmail(String token) {
        log.info("[VerifyEmail] Bắt đầu xác thực token");

        // 1. Tìm token
        VerificationToken verificationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> {
                    log.error("[VerifyEmail] Token không tồn tại");
                    return new IllegalArgumentException("Token xác thực không hợp lệ");
                });

        // 2. Kiểm tra token còn hiệu lực
        verificationToken.validate();

        // 3. Tìm user và xác thực
        User user = userRepository.findById(verificationToken.getUserId())
                .orElseThrow(() -> {
                    log.error("[VerifyEmail] User không tồn tại: userId={}", verificationToken.getUserId());
                    return new IllegalArgumentException("Người dùng không tồn tại");
                });

        if (user.isVerified()) {
            log.warn("User đã verify trước đó");
            return;
        }

        // 4. Cập nhật trạng thái
        user.verify();
        userRepository.save(user);

        // 5. Đánh dấu token đã sử dụng
        verificationToken.markAsUsed();
        tokenRepository.save(verificationToken);
        log.info("[VerifyEmail] Token đã đánh dấu used");

        log.info("[VerifyEmail] Hoàn thành xác thực email cho userId={}", user.getId());
    }
}
