package com.example.task_management.interfaces.mappers;

import com.example.task_management.application.dto.response.project.ProjectResponse;
import com.example.task_management.domain.entities.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectResponse toProjectResponse(Project project) {
        if (project == null) {
            return null;
        }

        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .ownerId(project.getOwnerId())
                .build();
    }
}
