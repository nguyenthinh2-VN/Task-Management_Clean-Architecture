package com.example.task_management.application.dto.request.task;

public class AssignTaskRequest {
    private Long assigneeId;

    public AssignTaskRequest() {}

    public Long getAssigneeId() { return assigneeId; }
    public void setAssigneeId(Long assigneeId) { this.assigneeId = assigneeId; }
}
