package com.example.task_management.domain.services.Task;

import com.example.task_management.domain.enums.TaskStatus;

public interface TaskStatusParser {
    TaskStatus parseStatus(String statusStr);
}
