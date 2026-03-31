package com.example.task_management.domain.services.impl;

import com.example.task_management.application.usecases.impl.task.MoveTaskUseCaseImpl;
import com.example.task_management.domain.enums.TaskStatus;
import com.example.task_management.domain.services.Task.TaskStatusParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TaskStatusParserImpl implements TaskStatusParser {
    private static final Logger log = LoggerFactory.getLogger(MoveTaskUseCaseImpl.class);

    @Override
    public TaskStatus parseStatus(String statusStr) {
        try {
            return TaskStatus.valueOf(statusStr);
        } catch (IllegalArgumentException e) {
            log.error("[MoveTask] Status không hợp lệ: {}", statusStr);
            throw new IllegalArgumentException("Trạng thái không hợp lệ: " + statusStr);
        }
    }
}
