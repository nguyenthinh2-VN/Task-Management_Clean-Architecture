package com.example.task_management.application.usecases.impl.project;

import com.example.task_management.application.repositories.ProjectRepository;
import com.example.task_management.application.usecases.project.DeleteProjectUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeleteProjectUseCaseImpl implements DeleteProjectUseCase {

    private final ProjectRepository projectRepository;

    public DeleteProjectUseCaseImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void deleteProject(Long projectId) {
        // TODO: implement logic
    }
}
