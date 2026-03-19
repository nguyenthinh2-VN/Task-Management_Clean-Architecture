package com.example.task_management.application.usecases.impl.project;

import com.example.task_management.application.dto.request.project.UpdateProjectRequest;
import com.example.task_management.application.dto.response.project.ProjectResponse;
import com.example.task_management.application.repositories.ProjectRepository;
import com.example.task_management.application.usecases.project.UpdateProjectUseCase;
import org.springframework.stereotype.Service;

@Service
public class UpdateProjectUseCaseImpl implements UpdateProjectUseCase {

    private final ProjectRepository projectRepository;

    public UpdateProjectUseCaseImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectResponse updateProject(Long projectId, UpdateProjectRequest request) {
        // TODO: implement logic
        return null;
    }
}
