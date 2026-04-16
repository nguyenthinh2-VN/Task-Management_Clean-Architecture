package com.example.task_management.domain.services.Task;

import com.example.task_management.domain.entities.Task;
import com.example.task_management.domain.enums.TaskStatus;

public interface TaskStatusTransitionService {
    void transitionTo(Task task, TaskStatus toStatus);

}
