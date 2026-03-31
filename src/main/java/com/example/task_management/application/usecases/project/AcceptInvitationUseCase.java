package com.example.task_management.application.usecases.project;

import com.example.task_management.application.DTOUsecase.response.project.ProjectMemberResult;

// UC08 – Chấp nhận lời mời
public interface AcceptInvitationUseCase {
    ProjectMemberResult acceptInvitation(Long invitationId, Long userId);
}
