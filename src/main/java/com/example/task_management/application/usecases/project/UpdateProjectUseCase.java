package com.example.task_management.application.usecases.project;

import com.example.task_management.application.dto.request.project.UpdateProjectRequest;
import com.example.task_management.application.dto.response.project.ProjectResponse;

// UC04 – Cập nhật project
public interface UpdateProjectUseCase {
    ProjectResponse updateProject(Long projectId, UpdateProjectRequest request);
}
