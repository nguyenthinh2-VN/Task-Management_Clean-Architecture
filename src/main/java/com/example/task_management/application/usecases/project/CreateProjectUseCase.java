package com.example.task_management.application.usecases.project;

import com.example.task_management.application.dto.request.project.CreateProjectRequest;
import com.example.task_management.application.dto.response.project.ProjectResponse;

// UC03 – Tạo project
public interface CreateProjectUseCase {
    ProjectResponse createProject(CreateProjectRequest request);
}
