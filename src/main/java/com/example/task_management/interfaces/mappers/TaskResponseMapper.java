package com.example.task_management.interfaces.mappers;

import com.example.task_management.application.DTOUsecase.response.task.TaskResult;
import com.example.task_management.interfaces.dto.response.task.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskResponseMapper {

    public TaskResponse toTaskResponse(TaskResult taskResult) {
        if (taskResult == null) {
            return null;
        }
        return TaskResponse.builder()
                .id(taskResult.getId())
                .title(taskResult.getTitle())
                .description(taskResult.getDescription())
                .status(taskResult.getStatus())
                .projectId(taskResult.getProjectId())
                .assigneeId(taskResult.getAssigneeId())
                .position(taskResult.getPosition())
                .build();
    }
}
