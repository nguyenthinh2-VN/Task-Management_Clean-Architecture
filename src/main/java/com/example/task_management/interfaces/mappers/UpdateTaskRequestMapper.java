 package com.example.task_management.interfaces.mappers;

import com.example.task_management.application.DTOUsecase.request.task.UpdateTaskCommand;
import com.example.task_management.application.DTOUsecase.request.task.UpdateTaskStatusCommand;
import com.example.task_management.interfaces.dto.request.task.UpdateTaskRequest;
import com.example.task_management.interfaces.dto.request.task.UpdateTaskStatusRequest;
import org.springframework.stereotype.Component;

/**
 * Mapper chuyển đổi từ Interface DTO (Request) sang Application DTO (Command).
 * Giúp tách biệt tầng Interface và Application theo Clean Architecture.
 */
@Component
public class UpdateTaskRequestMapper {

    public UpdateTaskCommand toCommand(UpdateTaskRequest request) {
        if (request == null) {
            return null;
        }
        return UpdateTaskCommand.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }

    public UpdateTaskStatusCommand toCommand(UpdateTaskStatusRequest request) {
        if (request == null) {
            return null;
        }
        return UpdateTaskStatusCommand.builder()
                .status(request.getStatus())
                .build();
    }
}
