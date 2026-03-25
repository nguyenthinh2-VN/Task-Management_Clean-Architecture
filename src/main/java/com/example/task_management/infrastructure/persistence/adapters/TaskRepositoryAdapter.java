package com.example.task_management.infrastructure.persistence.adapters;

import com.example.task_management.application.repositories.TaskRepository;
import com.example.task_management.domain.entities.Task;
import com.example.task_management.infrastructure.persistence.jparepositories.TaskJpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class TaskRepositoryAdapter implements TaskRepository {

    private final TaskJpaRepository taskJpaRepository;

    public TaskRepositoryAdapter(TaskJpaRepository taskJpaRepository) {
        this.taskJpaRepository = taskJpaRepository;
    }

    @Override
    public Task save(Task task) {
        // TODO: map domain → jpa, save, map back
        return null;
    }

    @Override
    public Optional<Task> findById(Long id) {
        // TODO: map
        return Optional.empty();
    }

    @Override
    public List<Task> findAllByProjectId(Long projectId) {
        // TODO: map list
        return List.of();
    }

    @Override
    public void deleteById(Long id) {
        taskJpaRepository.deleteById(id);
    }

    @Override
    public void deleteAllByProjectId(Long projectId) {
        taskJpaRepository.deleteAllByProjectId(projectId);
    }
}
