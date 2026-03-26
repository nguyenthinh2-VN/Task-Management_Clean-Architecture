package com.example.task_management.interfaces.controllers;

import com.example.task_management.application.dto.request.task.CreateTaskRequest;
import com.example.task_management.application.dto.response.ApiResponse;
import com.example.task_management.application.dto.response.task.TaskResponse;
import com.example.task_management.application.usecases.task.CreateTaskUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
    }

    // POST /api/projects/{projectId}/tasks
    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(
            @PathVariable Long projectId,
            @Valid @RequestBody CreateTaskRequest request,
            Authentication authentication) {

        TaskResponse taskResponse = createTaskUseCase.createTask(projectId, request, authentication.getName());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Tạo task thành công", taskResponse));
    }
}
