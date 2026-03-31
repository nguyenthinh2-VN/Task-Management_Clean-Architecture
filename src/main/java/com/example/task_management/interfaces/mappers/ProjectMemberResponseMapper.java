package com.example.task_management.interfaces.mappers;

import com.example.task_management.application.DTOUsecase.response.project.ProjectMemberResult;
import com.example.task_management.interfaces.dto.response.project.ProjectMemberResponse;
import org.springframework.stereotype.Component;

@Component
public class ProjectMemberResponseMapper {

    public ProjectMemberResponse toProjectMemberResponse(ProjectMemberResult projectMemberResult) {
        if (projectMemberResult == null) {
            return null;
        }
        ProjectMemberResponse response = new ProjectMemberResponse();
        response.setId(projectMemberResult.getId());
        response.setProjectId(projectMemberResult.getProjectId());
        response.setUserId(projectMemberResult.getUserId());
        response.setRole(projectMemberResult.getRole());
        response.setInvitationStatus(projectMemberResult.getInvitationStatus());
        return response;
    }
}
