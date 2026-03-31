package com.example.task_management.interfaces.mappers;

import com.example.task_management.application.DTOUsecase.response.project.ProjectResult;
import com.example.task_management.interfaces.dto.response.project.ProjectResponse;
import org.springframework.stereotype.Component;

@Component
public class ProjectResponseMapper {

    public ProjectResponse toProjectResponse(ProjectResult projectResult) {
        if (projectResult == null) {
            return null;
        }
        return ProjectResponse.builder()
                .id(projectResult.getId())
                .name(projectResult.getName())
                .description(projectResult.getDescription())
                .ownerId(projectResult.getOwnerId())
                .build();
    }
}
