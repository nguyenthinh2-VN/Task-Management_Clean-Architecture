package com.example.task_management.application.events;

/**
 * Event được publish khi một task được cập nhật thông tin (title, description).
 */
public class TaskUpdatedEvent {

    private final Long taskId;
    private final Long projectId;
    private final Long userId;
    private final String newTitle;
    private final String newDescription;
    private final String oldTitle;
    private final String oldDescription;

    public TaskUpdatedEvent(Long taskId, Long projectId, Long userId,
                            String newTitle, String newDescription,
                            String oldTitle, String oldDescription) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.userId = userId;
        this.newTitle = newTitle;
        this.newDescription = newDescription;
        this.oldTitle = oldTitle;
        this.oldDescription = oldDescription;
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

    public String getNewTitle() {
        return newTitle;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public String getOldTitle() {
        return oldTitle;
    }

    public String getOldDescription() {
        return oldDescription;
    }
}
