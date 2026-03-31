package com.example.task_management.interfaces.dto.request.task;

public class UpdateTaskRequest {
    private String title;
    private String description;

    public UpdateTaskRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
