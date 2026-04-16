package com.example.task_management.infrastructure.event;

import com.example.task_management.application.DTOUsecase.request.LogActivityRequest;
import com.example.task_management.application.events.TaskStatusUpdatedEvent;
import com.example.task_management.application.events.TaskUpdatedEvent;
import com.example.task_management.application.usecases.activitylog.LogActivityUseCase;
import com.example.task_management.domain.enums.ActionType;
import com.example.task_management.domain.enums.EntityType;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Listener xử lý các event liên quan đến Task.
 * Chịu trách nhiệm ghi log hoạt động khi task được cập nhật.
 */
@Component
public class TaskEventListener {

    private final LogActivityUseCase logActivityUseCase;

    public TaskEventListener(LogActivityUseCase logActivityUseCase) {
        this.logActivityUseCase = logActivityUseCase;
    }

    @EventListener
    public void handleTaskUpdated(TaskUpdatedEvent event) {
        logActivityUseCase.logActivity(LogActivityRequest.builder()
                .projectId(event.getProjectId())
                .userId(event.getUserId())
                .actionType(ActionType.TASK_UPDATED)
                .entityType(EntityType.TASK)
                .entityId(event.getTaskId())
                .description("Updated task: " + event.getNewTitle())
                .metadata(Map.of(
                        "oldTitle", event.getOldTitle() == null ? "" : event.getOldTitle(),
                        "newTitle", event.getNewTitle(),
                        "oldDescription", event.getOldDescription() == null ? "" : event.getOldDescription(),
                        "newDescription", event.getNewDescription() == null ? "" : event.getNewDescription()
                ))
                .build());
    }

    @EventListener
    public void handleTaskStatusUpdated(TaskStatusUpdatedEvent event) {
        logActivityUseCase.logActivity(LogActivityRequest.builder()
                .projectId(event.getProjectId())
                .userId(event.getUserId())
                .actionType(ActionType.TASK_MOVED)
                .entityType(EntityType.TASK)
                .entityId(event.getTaskId())
                .description("Moved task '" + event.getTaskTitle() + "' from " + event.getOldStatus() + " to " + event.getNewStatus())
                .metadata(Map.of(
                        "fromStatus", event.getOldStatus().name(),
                        "toStatus", event.getNewStatus().name()
                ))
                .build());
    }
}
