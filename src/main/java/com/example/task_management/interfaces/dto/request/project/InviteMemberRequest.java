package com.example.task_management.interfaces.dto.request.project;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteMemberRequest {
    
    @NotBlank(message = "Email người được mời không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String inviteeEmail;
    
}
