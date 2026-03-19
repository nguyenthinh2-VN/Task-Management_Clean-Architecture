package com.example.task_management.application.usecases.task;

import com.example.task_management.application.dto.request.task.UpdateTaskRequest;
import com.example.task_management.application.dto.response.task.TaskResponse;

// UC11 – Cập nhật task
public interface UpdateTaskUseCase {
    TaskResponse updateTask(Long taskId, UpdateTaskRequest request);
}
