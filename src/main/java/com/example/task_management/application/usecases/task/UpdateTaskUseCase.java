package com.example.task_management.application.usecases.task;

import com.example.task_management.application.DTOUsecase.request.task.UpdateTaskCommand;
import com.example.task_management.application.DTOUsecase.response.task.TaskResult;

// UC11 – Cập nhật task
public interface UpdateTaskUseCase {
    TaskResult updateTask(Long taskId, Long projectId, UpdateTaskCommand command, String userEmail);
}
