package com.example.task_management.application.usecases.task;

import com.example.task_management.interfaces.dto.request.task.UpdateTaskStatusRequest;
import com.example.task_management.application.DTOUsecase.response.task.TaskResult;

// UC14 – Cập nhật trạng thái task
public interface UpdateTaskStatusUseCase {
    TaskResult updateTaskStatus(Long taskId, UpdateTaskStatusRequest request);
}
