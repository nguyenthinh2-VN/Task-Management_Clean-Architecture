package com.example.task_management.interfaces.mappers;

import com.example.task_management.application.DTOUsecase.response.project.InvitationResult;
import com.example.task_management.interfaces.dto.response.project.InvitationResponse;
import org.springframework.stereotype.Component;

@Component
public class InvitationResponseMapper {

    public InvitationResponse toInvitationResponse(InvitationResult invitationResult) {
        if (invitationResult == null) {
            return null;
        }
        return InvitationResponse.builder()
                .id(invitationResult.getId())
                .projectId(invitationResult.getProjectId())
                .projectName(invitationResult.getProjectName())
                .role(invitationResult.getRole())
                .status(invitationResult.getStatus())
                .build();
    }
}
