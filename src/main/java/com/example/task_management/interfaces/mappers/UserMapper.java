package com.example.task_management.interfaces.mappers;

import com.example.task_management.application.DTOUsecase.response.auth.RegisterResult;
import com.example.task_management.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public RegisterResult toRegisterResponse(User user) {
        return RegisterResult.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
