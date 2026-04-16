package com.example.task_management.application.usecases.task;

import com.example.task_management.application.DTOUsecase.request.task.UpdateTaskStatusCommand;
import com.example.task_management.application.DTOUsecase.response.task.TaskResult;

// UC14 – Cập nhật trạng thái task
public interface UpdateTaskStatusUseCase {
    TaskResult updateTaskStatus(Long taskId, Long projectId, UpdateTaskStatusCommand command, String userEmail);
}
