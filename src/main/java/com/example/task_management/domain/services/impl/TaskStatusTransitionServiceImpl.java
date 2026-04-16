package com.example.task_management.domain.services.impl;

import com.example.task_management.domain.entities.Task;
import com.example.task_management.domain.enums.TaskStatus;
import com.example.task_management.domain.services.Task.TaskStatusTransitionService;
import org.springframework.stereotype.Service;

/**
 * Service chuyên trách chuyển đổi trạng thái Task.
 * Đảm bảo tuân thủ business rules được định nghĩa trong Domain Entity.
 */
@Service
public class TaskStatusTransitionServiceImpl implements TaskStatusTransitionService {

    @Override
    public void transitionTo(Task task, TaskStatus toStatus) {
        TaskStatus fromStatus = task.getStatus();

        if (fromStatus == toStatus) {
            return; // Không cần chuyển đổi nếu cùng trạng thái
        }

        switch (toStatus) {
            case TODO:
                transitionToTodo(task, fromStatus);
                break;
            case IN_PROGRESS:
                transitionToInProgress(task, fromStatus);
                break;
            case DONE:
                transitionToDone(task, fromStatus);
                break;
            case CANCELLED:
                task.cancel();
                break;
            default:
                throw new IllegalArgumentException("Trạng thái không hợp lệ: " + toStatus);
        }
    }

    private void transitionToTodo(Task task, TaskStatus fromStatus) {
        if (fromStatus == TaskStatus.CANCELLED) {
            // Restart cancelled task
            task.setStatus(TaskStatus.TODO);
        } else if (fromStatus != TaskStatus.TODO) {
            throw new IllegalStateException("Chỉ task đã CANCELLED mới được chuyển về TODO");
        }
    }

    private void transitionToInProgress(Task task, TaskStatus fromStatus) {
        if (fromStatus == TaskStatus.TODO) {
            task.start();
        } else if (fromStatus == TaskStatus.DONE) {
            // Reopen completed task
            task.setStatus(TaskStatus.IN_PROGRESS);
        } else if (fromStatus != TaskStatus.IN_PROGRESS) {
            throw new IllegalStateException("Task phải ở trạng thái TODO hoặc DONE để chuyển sang IN_PROGRESS");
        }
    }

    private void transitionToDone(Task task, TaskStatus fromStatus) {
        if (fromStatus == TaskStatus.IN_PROGRESS) {
            task.complete();
        } else if (fromStatus != TaskStatus.DONE) {
            throw new IllegalStateException("Task phải ở trạng thái IN_PROGRESS để hoàn thành");
        }
    }
}
