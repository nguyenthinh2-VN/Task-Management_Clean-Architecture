package com.example.task_management.application.usecases.impl.auth;

import com.example.task_management.interfaces.dto.request.auth.LoginRequest;
import com.example.task_management.application.DTOUsecase.response.auth.AuthResult;
import com.example.task_management.application.repositories.UserRepository;
import com.example.task_management.application.usecases.auth.LoginUseCase;
import com.example.task_management.domain.entities.User;
import com.example.task_management.domain.exceptions.UserNotVerifiedException;
import com.example.task_management.infrastructure.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginUseCaseImpl(UserRepository userRepository,
                            PasswordEncoder passwordEncoder,
                            JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthResult login(LoginRequest request) {
        // 1. Tìm user theo email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email hoặc mật khẩu không đúng"));

        // 2. Kiểm tra xác minh
        if (!user.isVerified()) {
            throw new UserNotVerifiedException("Tài khoản chưa được xác minh. Vui lòng xác minh email trước khi đăng nhập.");
        }

        // 3. Kiểm tra password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Email hoặc mật khẩu không đúng");
        }

        // 4. Tạo JWT token
        String token = jwtTokenProvider.generateToken(user.getEmail());

        // 5. Trả về token và trạng thái xác minh
        return AuthResult.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .verified(user.isVerified())
                .build();
    }
}
