package com.example.task_management.application.usecases.project;

import com.example.task_management.application.dto.response.project.ProjectMemberResponse;

// UC08 – Chấp nhận lời mời
public interface AcceptInvitationUseCase {
    ProjectMemberResponse acceptInvitation(Long invitationId, Long userId);
}
