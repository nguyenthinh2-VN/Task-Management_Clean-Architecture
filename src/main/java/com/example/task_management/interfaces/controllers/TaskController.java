package com.example.task_management.interfaces.controllers;

import com.example.task_management.application.dto.request.task.*;
import com.example.task_management.application.dto.response.task.TaskResponse;
import com.example.task_management.application.usecases.task.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// [DI] inject tất cả task use cases qua interface
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final AssignTaskUseCase assignTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final GetTaskListUseCase getTaskListUseCase;
    private final UpdateTaskStatusUseCase updateTaskStatusUseCase;
    private final AddTaskDescriptionUseCase addTaskDescriptionUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase,
                          AssignTaskUseCase assignTaskUseCase,
                          UpdateTaskUseCase updateTaskUseCase,
                          DeleteTaskUseCase deleteTaskUseCase,
                          GetTaskListUseCase getTaskListUseCase,
                          UpdateTaskStatusUseCase updateTaskStatusUseCase,
                          AddTaskDescriptionUseCase addTaskDescriptionUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.assignTaskUseCase = assignTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.getTaskListUseCase = getTaskListUseCase;
        this.updateTaskStatusUseCase = updateTaskStatusUseCase;
        this.addTaskDescriptionUseCase = addTaskDescriptionUseCase;
    }

    // UC09 – Tạo task
    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody CreateTaskRequest request) {
        return ResponseEntity.ok(createTaskUseCase.createTask(request));
    }

    // UC10 – Gán task
    @PatchMapping("/{id}/assign")
    public ResponseEntity<TaskResponse> assign(@PathVariable Long id, @RequestBody AssignTaskRequest request) {
        return ResponseEntity.ok(assignTaskUseCase.assignTask(id, request));
    }

    // UC11 – Cập nhật task
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        return ResponseEntity.ok(updateTaskUseCase.updateTask(id, request));
    }

    // UC12 – Xóa task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteTaskUseCase.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // UC13 – Xem danh sách task
    @GetMapping
    public ResponseEntity<List<TaskResponse>> list(@RequestParam Long projectId) {
        return ResponseEntity.ok(getTaskListUseCase.getTasksByProject(projectId));
    }

    // UC14 – Cập nhật trạng thái task
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateStatus(@PathVariable Long id, @RequestBody UpdateTaskStatusRequest request) {
        return ResponseEntity.ok(updateTaskStatusUseCase.updateTaskStatus(id, request));
    }

    // UC15 – Thêm mô tả task
    @PatchMapping("/{id}/description")
    public ResponseEntity<TaskResponse> addDescription(@PathVariable Long id, @RequestBody AddTaskDescriptionRequest request) {
        return ResponseEntity.ok(addTaskDescriptionUseCase.addTaskDescription(id, request));
    }
}
