package com.example.task_management.application.dto.request.task;

import com.example.task_management.domain.enums.TaskStatus;

public class UpdateTaskStatusRequest {
    private TaskStatus status;

    public UpdateTaskStatusRequest() {}

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
}
