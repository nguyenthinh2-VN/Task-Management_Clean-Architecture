package com.example.task_management.application.usecases.task;

import com.example.task_management.application.dto.request.task.CreateTaskRequest;
import com.example.task_management.application.dto.response.task.TaskResponse;

// UC09 – Tạo task
public interface CreateTaskUseCase {
    TaskResponse createTask(CreateTaskRequest request);
}
