package com.example.task_management.application.usecases.project;

import com.example.task_management.application.dto.request.project.InviteMemberRequest;
import com.example.task_management.application.dto.response.project.ProjectMemberResponse;

// UC07 – Mời thành viên
public interface InviteMemberUseCase {
    ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request);
}
