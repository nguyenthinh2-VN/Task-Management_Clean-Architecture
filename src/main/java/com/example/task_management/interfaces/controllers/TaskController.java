package com.example.task_management.interfaces.controllers;

import com.example.task_management.application.dto.request.task.CreateTaskRequest;
import com.example.task_management.application.dto.response.ApiResponse;
import com.example.task_management.application.dto.response.task.TaskResponse;
import com.example.task_management.application.usecases.task.CreateTaskUseCase;
import com.example.task_management.application.usecases.task.GetTaskUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase, GetTaskUseCase getTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
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

    // GET /api/projects/{projectId}/tasks?status=TODO
    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getTasks(
            @PathVariable Long projectId,
            @RequestParam(required = false) String status,
            Authentication authentication) {

        List<TaskResponse> tasks = getTaskUseCase.getTasks(projectId, status, authentication.getName());
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Lấy danh sách task thành công", tasks));
    }
}
