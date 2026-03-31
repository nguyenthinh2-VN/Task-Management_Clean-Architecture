package com.example.task_management.application.usecases.task;

import com.example.task_management.interfaces.dto.request.task.UpdateTaskRequest;
import com.example.task_management.application.DTOUsecase.response.task.TaskResult;

// UC11 – Cập nhật task
public interface UpdateTaskUseCase {
    TaskResult updateTask(Long taskId, UpdateTaskRequest request);
}
