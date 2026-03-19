package com.example.task_management.application.repositories;

import com.example.task_management.domain.entities.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    Project save(Project project);
    Optional<Project> findById(Long id);
    List<Project> findAllByOwnerId(Long ownerId);
    void deleteById(Long id);
}
