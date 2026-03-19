package com.example.task_management.interfaces.mappers;

import com.example.task_management.application.dto.response.auth.RegisterResponse;
import com.example.task_management.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public RegisterResponse toRegisterResponse(User user) {
        return new RegisterResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
