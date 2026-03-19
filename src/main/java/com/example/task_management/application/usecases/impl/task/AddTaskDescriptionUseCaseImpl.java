package com.example.task_management.application.usecases.impl.task;

import com.example.task_management.application.dto.request.task.AddTaskDescriptionRequest;
import com.example.task_management.application.dto.response.task.TaskResponse;
import com.example.task_management.application.repositories.TaskRepository;
import com.example.task_management.application.usecases.task.AddTaskDescriptionUseCase;
import org.springframework.stereotype.Service;

@Service
public class AddTaskDescriptionUseCaseImpl implements AddTaskDescriptionUseCase {

    private final TaskRepository taskRepository;

    public AddTaskDescriptionUseCaseImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponse addTaskDescription(Long taskId, AddTaskDescriptionRequest request) {
        // TODO: implement logic
        return null;
    }
}
