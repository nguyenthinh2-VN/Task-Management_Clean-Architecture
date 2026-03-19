package com.example.task_management.interfaces.mappers;

import com.example.task_management.application.dto.response.project.ProjectMemberResponse;
import com.example.task_management.application.dto.response.project.ProjectResponse;
import com.example.task_management.domain.entities.Project;
import com.example.task_management.domain.entities.ProjectMember;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectResponse toProjectResponse(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setOwnerId(project.getOwnerId());
        return response;
    }

    public ProjectMemberResponse toProjectMemberResponse(ProjectMember member) {
        ProjectMemberResponse response = new ProjectMemberResponse();
        response.setId(member.getId());
        response.setProjectId(member.getProjectId());
        response.setUserId(member.getUserId());
        response.setRole(member.getRole());
        response.setInvitationStatus(member.getInvitationStatus());
        return response;
    }
}
