package com.example.task_management.application.usecases.impl.auth;

import com.example.task_management.application.dto.request.auth.RegisterRequest;
import com.example.task_management.application.dto.response.auth.RegisterResponse;
import com.example.task_management.application.repositories.UserRepository;
import com.example.task_management.application.usecases.auth.RegisterUseCase;
import com.example.task_management.domain.entities.User;
import com.example.task_management.interfaces.mappers.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUseCaseImpl implements RegisterUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public RegisterUseCaseImpl(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        // 1. Kiểm tra email đã tồn tại chưa
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email đã được sử dụng: " + request.getEmail());
        }

        // 2. Tạo domain entity, hash password
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setVerified(false);

        // 3. Lưu vào DB
        User savedUser = userRepository.save(newUser);

        // 4. Convert → DTO response
        return userMapper.toRegisterResponse(savedUser);
    }
}
