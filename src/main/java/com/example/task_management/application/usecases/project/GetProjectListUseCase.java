package com.example.task_management.application.usecases.project;

import com.example.task_management.application.dto.response.project.ProjectResponse;
import java.util.List;

// UC06 – Xem danh sách project
public interface GetProjectListUseCase {
    List<ProjectResponse> getProjectsByOwner(Long ownerId);
}
