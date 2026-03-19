package com.example.task_management.interfaces.mappers;

import com.example.task_management.application.dto.response.task.TaskResponse;
import com.example.task_management.domain.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponse toTaskResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setProjectId(task.getProjectId());
        response.setAssigneeId(task.getAssigneeId());
        return response;
    }
}
