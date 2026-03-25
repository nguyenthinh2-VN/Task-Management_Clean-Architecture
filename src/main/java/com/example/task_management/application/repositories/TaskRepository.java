package com.example.task_management.application.repositories;

import com.example.task_management.domain.entities.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findAllByProjectId(Long projectId);
    void deleteById(Long id);
    void deleteAllByProjectId(Long projectId);
}
