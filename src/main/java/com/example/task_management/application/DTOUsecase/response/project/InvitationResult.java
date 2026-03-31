package com.example.task_management.application.DTOUsecase.response.project;

import com.example.task_management.domain.enums.InvitationStatus;
import com.example.task_management.domain.enums.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvitationResult {
    private Long id;
    private Long projectId;
    private String projectName;
    private MemberRole role;
    private InvitationStatus status;
}
