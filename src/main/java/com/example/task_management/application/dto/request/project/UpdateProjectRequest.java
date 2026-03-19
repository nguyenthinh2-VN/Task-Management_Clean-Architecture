package com.example.task_management.application.dto.request.project;

public class UpdateProjectRequest {
    private String name;
    private String description;

    public UpdateProjectRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
