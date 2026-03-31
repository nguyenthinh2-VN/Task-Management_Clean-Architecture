package com.example.task_management.interfaces.mappers;

import com.example.task_management.application.DTOUsecase.response.auth.AuthResult;
import com.example.task_management.interfaces.dto.response.auth.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthResponseMapper {

    public LoginResponse toLoginResponse(AuthResult authResult) {
        if (authResult == null) {
            return null;
        }
        return new LoginResponse(authResult.getAccessToken(), authResult.getTokenType());
    }
}
