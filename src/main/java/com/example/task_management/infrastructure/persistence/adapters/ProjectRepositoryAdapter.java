package com.example.task_management.infrastructure.persistence.adapters;

import com.example.task_management.application.repositories.ProjectRepository;
import com.example.task_management.domain.entities.Project;
import com.example.task_management.infrastructure.persistence.jparepositories.ProjectJpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ProjectRepositoryAdapter implements ProjectRepository {

    private final ProjectJpaRepository projectJpaRepository;

    public ProjectRepositoryAdapter(ProjectJpaRepository projectJpaRepository) {
        this.projectJpaRepository = projectJpaRepository;
    }

    @Override
    public Project save(Project project) {
        // TODO: map domain → jpa, save, map back
        return null;
    }

    @Override
    public Optional<Project> findById(Long id) {
        // TODO: map
        return Optional.empty();
    }

    @Override
    public List<Project> findAllByOwnerId(Long ownerId) {
        // TODO: map list
        return List.of();
    }

    @Override
    public void deleteById(Long id) {
        projectJpaRepository.deleteById(id);
    }
}
