package com.example.task_management.application.usecases.impl.auth;

import com.example.task_management.application.dto.request.auth.LoginRequest;
import com.example.task_management.application.dto.response.auth.LoginResponse;
import com.example.task_management.application.repositories.UserRepository;
import com.example.task_management.application.usecases.auth.LoginUseCase;
import com.example.task_management.domain.entities.User;
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
    public LoginResponse login(LoginRequest request) {
        // 1. Tìm user theo email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email hoặc mật khẩu không đúng"));

        // 2. Kiểm tra password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Email hoặc mật khẩu không đúng");
        }

        // 3. Tạo JWT token
        String token = jwtTokenProvider.generateToken(user.getEmail());

        // 4. Trả về token
        return new LoginResponse(token);
    }
}
