package com.example.task_management.application.DTOUsecase.request.task;

import com.example.task_management.domain.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Command để cập nhật trạng thái task.
 * Được tạo từ Interface Layer qua Mapper.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskStatusCommand {
    private TaskStatus status;
}
