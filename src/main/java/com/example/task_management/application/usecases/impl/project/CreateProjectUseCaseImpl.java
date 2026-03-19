package com.example.task_management.application.usecases.impl.project;

import com.example.task_management.application.dto.request.project.CreateProjectRequest;
import com.example.task_management.application.dto.response.project.ProjectResponse;
import com.example.task_management.application.repositories.ProjectRepository;
import com.example.task_management.application.usecases.project.CreateProjectUseCase;
import com.example.task_management.interfaces.mappers.ProjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectUseCaseImpl implements CreateProjectUseCase {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public CreateProjectUseCaseImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectResponse createProject(CreateProjectRequest request) {
        // TODO: implement logic
        return null;
    }
}
