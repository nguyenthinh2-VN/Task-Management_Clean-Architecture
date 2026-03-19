package com.example.task_management.application.usecases.impl.task;

import com.example.task_management.application.dto.request.task.AssignTaskRequest;
import com.example.task_management.application.dto.response.task.TaskResponse;
import com.example.task_management.application.repositories.TaskRepository;
import com.example.task_management.application.usecases.task.AssignTaskUseCase;
import org.springframework.stereotype.Service;

@Service
public class AssignTaskUseCaseImpl implements AssignTaskUseCase {

    private final TaskRepository taskRepository;

    public AssignTaskUseCaseImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponse assignTask(Long taskId, AssignTaskRequest request) {
        // TODO: implement logic
        return null;
    }
}
