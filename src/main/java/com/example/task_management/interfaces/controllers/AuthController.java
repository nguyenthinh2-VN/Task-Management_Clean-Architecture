package com.example.task_management.interfaces.controllers;

import com.example.task_management.application.DTOUsecase.response.auth.AuthResult;
import com.example.task_management.application.DTOUsecase.response.auth.RegisterResult;
import com.example.task_management.interfaces.dto.request.auth.GoogleLoginRequest;
import com.example.task_management.interfaces.dto.request.auth.LoginRequest;
import com.example.task_management.interfaces.dto.request.auth.RegisterRequest;
import com.example.task_management.interfaces.dto.response.ApiResponse;
import com.example.task_management.interfaces.dto.response.auth.LoginResponse;
import com.example.task_management.interfaces.dto.response.auth.RegisterResponse;
import com.example.task_management.application.usecases.auth.GoogleLoginUseCase;
import com.example.task_management.application.usecases.auth.LoginUseCase;
import com.example.task_management.application.usecases.auth.RegisterUseCase;
import com.example.task_management.application.usecases.auth.ResendVerificationUseCase;
import com.example.task_management.application.usecases.auth.VerifyEmailUseCase;
import com.example.task_management.interfaces.mappers.AuthResponseMapper;
import com.example.task_management.interfaces.mappers.RegisterResponseMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final GoogleLoginUseCase googleLoginUseCase;
    private final VerifyEmailUseCase verifyEmailUseCase;
    private final ResendVerificationUseCase resendVerificationUseCase;
    private final AuthResponseMapper authResponseMapper;
    private final RegisterResponseMapper registerResponseMapper;

    public AuthController(RegisterUseCase registerUseCase, LoginUseCase loginUseCase, 
                          GoogleLoginUseCase googleLoginUseCase,
                          VerifyEmailUseCase verifyEmailUseCase,
                          ResendVerificationUseCase resendVerificationUseCase,
                          AuthResponseMapper authResponseMapper,
                          RegisterResponseMapper registerResponseMapper) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.googleLoginUseCase = googleLoginUseCase;
        this.verifyEmailUseCase = verifyEmailUseCase;
        this.resendVerificationUseCase = resendVerificationUseCase;
        this.authResponseMapper = authResponseMapper;
        this.registerResponseMapper = registerResponseMapper;
    }

    // UC01 – Đăng ký
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResult result = registerUseCase.register(request);
        RegisterResponse data = registerResponseMapper.toRegisterResponse(result);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Đăng ký thành công", data));
    }

    // UC02 – Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResult result = loginUseCase.login(request);
        LoginResponse data = authResponseMapper.toLoginResponse(result);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Đăng nhập thành công", data));
    }

    // UC03 – Đăng nhập bằng Google
    @PostMapping("/login/google")
    public ResponseEntity<ApiResponse<LoginResponse>> loginWithGoogle(@Valid @RequestBody GoogleLoginRequest request) {
        AuthResult result = googleLoginUseCase.loginWithGoogle(request);
        LoginResponse data = authResponseMapper.toLoginResponse(result);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Đăng nhập Google thành công", data));
    }

    // UC04 – Xác thực email
    @GetMapping("/verify")
    public ResponseEntity<ApiResponse<Void>> verifyEmail(@RequestParam String token) {
        verifyEmailUseCase.verifyEmail(token);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Xác thực email thành công", null));
    }

    // UC05 – Gửi lại email xác thực
    @PostMapping("/resend-verification")
    public ResponseEntity<ApiResponse<Void>> resendVerificationEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Email là bắt buộc", null));
        }
        resendVerificationUseCase.resendVerificationEmail(email);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), 
                "Email xác thực đã được gửi lại. Vui lòng kiểm tra hộp thư.", null));
    }
}
