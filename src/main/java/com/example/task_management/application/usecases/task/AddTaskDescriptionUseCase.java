package com.example.task_management.application.usecases.task;

import com.example.task_management.application.dto.request.task.AddTaskDescriptionRequest;
import com.example.task_management.application.dto.response.task.TaskResponse;

// UC15 – Thêm mô tả task
public interface AddTaskDescriptionUseCase {
    TaskResponse addTaskDescription(Long taskId, AddTaskDescriptionRequest request);
}
