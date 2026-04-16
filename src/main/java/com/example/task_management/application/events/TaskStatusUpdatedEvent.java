package com.example.task_management.application.events;

import com.example.task_management.domain.enums.TaskStatus;

/**
 * Event được publish khi trạng thái task được cập nhật.
 */
public class TaskStatusUpdatedEvent {

    private final Long taskId;
    private final Long projectId;
    private final Long userId;
    private final TaskStatus newStatus;
    private final TaskStatus oldStatus;
    private final String taskTitle;

    public TaskStatusUpdatedEvent(Long taskId, Long projectId, Long userId,
                                  TaskStatus newStatus, TaskStatus oldStatus,
                                  String taskTitle) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.userId = userId;
        this.newStatus = newStatus;
        this.oldStatus = oldStatus;
        this.taskTitle = taskTitle;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public TaskStatus getNewStatus() {
        return newStatus;
    }

    public TaskStatus getOldStatus() {
        return oldStatus;
    }

    public String getTaskTitle() {
        return taskTitle;
    }
}
