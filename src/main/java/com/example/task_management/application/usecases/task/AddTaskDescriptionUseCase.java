package com.example.task_management.application.usecases.task;

import com.example.task_management.interfaces.dto.request.task.AddTaskDescriptionRequest;
import com.example.task_management.application.DTOUsecase.response.task.TaskResult;

// UC15 – Thêm mô tả task
public interface AddTaskDescriptionUseCase {
    TaskResult addTaskDescription(Long taskId, AddTaskDescriptionRequest request);
}
