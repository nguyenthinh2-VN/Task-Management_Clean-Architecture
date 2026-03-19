package com.example.task_management.application.usecases.task;

import com.example.task_management.application.dto.request.task.UpdateTaskStatusRequest;
import com.example.task_management.application.dto.response.task.TaskResponse;

// UC14 – Cập nhật trạng thái task
public interface UpdateTaskStatusUseCase {
    TaskResponse updateTaskStatus(Long taskId, UpdateTaskStatusRequest request);
}
