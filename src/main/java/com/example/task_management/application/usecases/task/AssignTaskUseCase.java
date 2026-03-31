package com.example.task_management.application.usecases.task;

import com.example.task_management.interfaces.dto.request.task.AssignTaskRequest;
import com.example.task_management.application.DTOUsecase.response.task.TaskResult;

// UC10 – Gán task
public interface AssignTaskUseCase {
    TaskResult assignTask(Long projectId, Long taskId, AssignTaskRequest request, String userEmail);
}
