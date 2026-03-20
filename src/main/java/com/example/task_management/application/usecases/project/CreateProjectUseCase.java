package com.example.task_management.application.usecases.project;

import com.example.task_management.application.dto.request.project.CreateProjectRequest;
import com.example.task_management.application.dto.response.project.ProjectResponse;

public interface CreateProjectUseCase {
    
    /**
     * Tạo dự án mới 
     * @param request Thông tin dự án
     * @param currentUserEmail Email của người đang đăng nhập (được trích xuất từ Token JWT)
     * @return ProjectResponse
     */
    ProjectResponse createProject(CreateProjectRequest request, String currentUserEmail);
}
