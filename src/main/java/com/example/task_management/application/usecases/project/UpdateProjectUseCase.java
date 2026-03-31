package com.example.task_management.application.usecases.project;

import com.example.task_management.interfaces.dto.request.project.UpdateProjectRequest;
import com.example.task_management.application.DTOUsecase.response.project.ProjectResult;

// UC04 – Cập nhật project
public interface UpdateProjectUseCase {
    ProjectResult updateProject(Long projectId, UpdateProjectRequest request);
}
