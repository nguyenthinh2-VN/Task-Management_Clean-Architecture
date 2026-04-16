package com.example.task_management.application.DTOUsecase.request.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Command để cập nhật thông tin task (title, description).
 * Được tạo từ Interface Layer qua Mapper.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskCommand {
    private String title;
    private String description;
}
