package com.example.task_management.application.usecases.task;

import com.example.task_management.application.dto.response.task.TaskResponse;
import java.util.List;

// UC13 – Xem danh sách task
public interface GetTaskListUseCase {
    List<TaskResponse> getTasksByProject(Long projectId);
}
