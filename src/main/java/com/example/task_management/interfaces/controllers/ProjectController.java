package com.example.task_management.interfaces.controllers;

import com.example.task_management.application.dto.request.project.CreateProjectRequest;
import com.example.task_management.application.dto.response.ApiResponse;
import com.example.task_management.application.dto.response.project.ProjectResponse;
import com.example.task_management.application.usecases.project.CreateProjectUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;

    public ProjectController(CreateProjectUseCase createProjectUseCase) {
        this.createProjectUseCase = createProjectUseCase;
    }

    // API: Tạo dự án mới
    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> createProject(
            @Valid @RequestBody CreateProjectRequest request,
            Authentication authentication) {

        // Lấy Email của user đang đăng nhập thông qua SecurityContext (được giải mã từ
        // JWT token gửi lên Header)
        String currentUserEmail = authentication.getName();

        // Pass việc xử lý vào tầng UseCase
        ProjectResponse responseData = createProjectUseCase.createProject(request, currentUserEmail);

        // Chuẩn hóa chuỗi trả về
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Dự án đã được tạo thành công", responseData));
    }

}
