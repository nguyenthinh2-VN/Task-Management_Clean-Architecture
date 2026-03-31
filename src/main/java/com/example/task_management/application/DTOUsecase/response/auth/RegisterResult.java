package com.example.task_management.application.DTOUsecase.response.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResult {
    private Long id;
    private String username;
    private String email;
}
