package com.example.task_management.application.usecases.impl.project;

import com.example.task_management.application.DTOUsecase.response.project.ProjectResult;
import com.example.task_management.application.repositories.ProjectRepository;
import com.example.task_management.application.usecases.project.GetProjectListUseCase;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetProjectListUseCaseImpl implements GetProjectListUseCase {

    private final ProjectRepository projectRepository;

    public GetProjectListUseCaseImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectResult> getProjectsByOwner(Long ownerId) {
        // TODO: implement logic
        return null;
    }
}
