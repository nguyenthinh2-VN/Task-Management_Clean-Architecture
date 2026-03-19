package com.example.task_management.application.usecases.impl.task;

import com.example.task_management.application.dto.response.task.TaskResponse;
import com.example.task_management.application.repositories.TaskRepository;
import com.example.task_management.application.usecases.task.GetTaskListUseCase;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetTaskListUseCaseImpl implements GetTaskListUseCase {

    private final TaskRepository taskRepository;

    public GetTaskListUseCaseImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskResponse> getTasksByProject(Long projectId) {
        // TODO: implement logic
        return null;
    }
}
