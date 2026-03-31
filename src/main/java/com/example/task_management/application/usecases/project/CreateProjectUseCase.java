package com.example.task_management.application.usecases.project;

import com.example.task_management.interfaces.dto.request.project.CreateProjectRequest;
import com.example.task_management.application.DTOUsecase.response.project.ProjectResult;

public interface CreateProjectUseCase {
    
    /**
     * Tạo dự án mới 
     * @param request Thông tin dự án
     * @param currentUserEmail Email của người đang đăng nhập (được trích xuất từ Token JWT)
     * @return ProjectResult
     */
    ProjectResult createProject(CreateProjectRequest request, String currentUserEmail);
}
