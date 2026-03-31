package com.example.task_management.interfaces.mappers;

import com.example.task_management.application.DTOUsecase.response.auth.RegisterResult;
import com.example.task_management.interfaces.dto.response.auth.RegisterResponse;
import org.springframework.stereotype.Component;

@Component
public class RegisterResponseMapper {

    public RegisterResponse toRegisterResponse(RegisterResult registerResult) {
        if (registerResult == null) {
            return null;
        }
        return new RegisterResponse(
                registerResult.getId(),
                registerResult.getUsername(),
                registerResult.getEmail()
        );
    }
}
