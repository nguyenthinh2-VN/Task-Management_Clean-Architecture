package com.example.task_management.application.usecases.impl.task;

import com.example.task_management.application.DTOUsecase.request.task.UpdateTaskCommand;
import com.example.task_management.application.DTOUsecase.response.task.TaskResult;
import com.example.task_management.application.events.TaskUpdatedEvent;
import com.example.task_management.application.mapper.TaskMapper;
import com.example.task_management.application.repositories.ProjectMemberRepository;
import com.example.task_management.application.repositories.TaskRepository;
import com.example.task_management.application.repositories.UserRepository;
import com.example.task_management.application.usecases.task.UpdateTaskUseCase;
import com.example.task_management.domain.entities.ProjectMember;
import com.example.task_management.domain.entities.Task;
import com.example.task_management.domain.entities.User;
import com.example.task_management.domain.enums.InvitationStatus;
import com.example.task_management.domain.services.PermissionService;
import com.example.task_management.interfaces.exceptions.ProjectAccessDeniedException;
import com.example.task_management.interfaces.exceptions.TaskNotFoundException;
import com.example.task_management.interfaces.exceptions.UserNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UC11 – Cập nhật thông tin task (title, description).
 * Use Case đóng vai trò Orchestrator, chỉ điều phối các domain objects và services.
 */
@Service
public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final PermissionService permissionService;

    public UpdateTaskUseCaseImpl(
            TaskRepository taskRepository,
            TaskMapper taskMapper,
            ApplicationEventPublisher eventPublisher,
            PermissionService permissionService) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.eventPublisher = eventPublisher;
        this.permissionService = permissionService;
    }

    @Override
    @Transactional
    public TaskResult updateTask(Long taskId, Long projectId, UpdateTaskCommand command, String userEmail) {
        // 1. Validate: User phải tồn tại
        User user = permissionService.validateProjectMember(projectId, userEmail);


        // 2. Validate: Task phải tồn tại và thuộc project
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task không tồn tại"));

        if (!task.belongsToProject(projectId)) {
            throw new TaskNotFoundException("Task không thuộc dự án này");
        }
        

        // 4. Lưu giá trị cũ trước khi update (cho event)
        String oldTitle = task.getTitle();
        String oldDescription = task.getDescription();

        // 5. Update task thông qua domain methods
        task.updateContent(command.getTitle(), command.getDescription());

        // 6. Lưu task
        Task updatedTask = taskRepository.save(task);

        // 7. Publish event (decoupled logging)
        eventPublisher.publishEvent(new TaskUpdatedEvent(
                updatedTask.getId(),
                projectId,
                user.getId(),
                updatedTask.getTitle(),
                updatedTask.getDescription(),
                oldTitle,
                oldDescription
        ));

        // 8. Return kết quả
        return taskMapper.toTaskResult(updatedTask);
    }
}
