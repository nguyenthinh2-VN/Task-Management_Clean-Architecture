package com.example.task_management.application.usecases.impl.task;

import com.example.task_management.interfaces.dto.request.task.UpdateTaskRequest;
import com.example.task_management.application.DTOUsecase.response.task.TaskResult;
import com.example.task_management.application.repositories.TaskRepository;
import com.example.task_management.application.usecases.task.UpdateTaskUseCase;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {

    private final TaskRepository taskRepository;

    public UpdateTaskUseCaseImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResult updateTask(Long taskId, UpdateTaskRequest request) {
        // TODO: implement logic
        return null;
    }
}
