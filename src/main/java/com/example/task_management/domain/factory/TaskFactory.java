package com.example.task_management.domain.factory;

import com.example.task_management.domain.entities.Task;
import com.example.task_management.domain.enums.TaskStatus;

/**
 * Factory tạo Task Domain Entity.
 * Status được thiết lập là TODO, position = (số task hiện tại + 1).
 * Không cho phép set status trực tiếp từ bên ngoài.
 */
public class TaskFactory {

    public static Task create(String title, String description, Long projectId, int currentTaskCount) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Tiêu đề task không được để trống");
        }
        if (projectId == null) {
            throw new IllegalArgumentException("Task phải thuộc một dự án");
        }

        Task task = new Task();
        task.setTitle(title.trim());
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setAssigneeId(null);                     // Mặc định chưa assign
        task.setPosition(currentTaskCount + 1);        // Thêm vào cuối danh sách
        task.setStatus(TaskStatus.TODO);               // Package-private setter trong cùng package domain

        return task;
    }
}
