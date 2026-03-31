package com.example.task_management.application.usecases.impl.task;

import com.example.task_management.interfaces.dto.request.task.UpdateTaskStatusRequest;
import com.example.task_management.application.DTOUsecase.response.task.TaskResult;
import com.example.task_management.application.repositories.TaskRepository;
import com.example.task_management.application.usecases.task.UpdateTaskStatusUseCase;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskStatusUseCaseImpl implements UpdateTaskStatusUseCase {

    private final TaskRepository taskRepository;

    public UpdateTaskStatusUseCaseImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResult updateTaskStatus(Long taskId, UpdateTaskStatusRequest request) {
        // TODO: implement logic
        return null;
    }
}
