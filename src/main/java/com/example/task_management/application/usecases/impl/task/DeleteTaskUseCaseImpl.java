package com.example.task_management.application.usecases.impl.task;

import com.example.task_management.application.repositories.TaskRepository;
import com.example.task_management.application.usecases.task.DeleteTaskUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskUseCaseImpl implements DeleteTaskUseCase {

    private final TaskRepository taskRepository;

    public DeleteTaskUseCaseImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void deleteTask(Long taskId) {
        // TODO: implement logic
    }
}
