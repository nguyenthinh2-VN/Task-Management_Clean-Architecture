package com.example.task_management.application.usecases.impl.task;

import com.example.task_management.application.dto.request.task.CreateTaskRequest;
import com.example.task_management.application.dto.response.task.TaskResponse;
import com.example.task_management.application.repositories.TaskRepository;
import com.example.task_management.application.usecases.task.CreateTaskUseCase;
import com.example.task_management.interfaces.mappers.TaskMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public CreateTaskUseCaseImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {
        // TODO: implement logic
        return null;
    }
}
