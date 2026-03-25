package com.example.task_management.domain.factory;

import com.example.task_management.domain.entities.Project;

public class ProjectFactory {
    public static Project create(String name, String description, Long ownerId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Project name is required");
        }

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setOwnerId(ownerId);

        return project;
    }
}
