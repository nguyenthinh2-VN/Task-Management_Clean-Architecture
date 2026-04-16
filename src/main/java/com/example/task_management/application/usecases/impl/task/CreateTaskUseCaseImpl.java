package com.example.task_management.application.usecases.impl.task;

import com.example.task_management.interfaces.dto.request.task.CreateTaskRequest;
import com.example.task_management.application.DTOUsecase.response.task.TaskResult;
import com.example.task_management.application.repositories.ProjectRepository;
import com.example.task_management.application.repositories.TaskRepository;
import com.example.task_management.application.usecases.task.CreateTaskUseCase;
import com.example.task_management.domain.entities.Task;
import com.example.task_management.domain.entities.User;
import com.example.task_management.domain.factory.TaskFactory;
import com.example.task_management.domain.services.PermissionService;
import com.example.task_management.application.usecases.activitylog.LogActivityUseCase;
import com.example.task_management.application.DTOUsecase.request.LogActivityRequest;
import com.example.task_management.domain.enums.ActionType;
import com.example.task_management.domain.enums.EntityType;
import java.util.Map;
import com.example.task_management.application.mapper.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

        private static final Logger log = LoggerFactory.getLogger(CreateTaskUseCaseImpl.class);

        private final ProjectRepository projectRepository;
        private final TaskRepository taskRepository;
        private final TaskMapper taskMapper;
        private final LogActivityUseCase logActivityUseCase;
        private final PermissionService permissionService;

        public CreateTaskUseCaseImpl(
                        ProjectRepository projectRepository,
                        TaskRepository taskRepository,
                        TaskMapper taskMapper,
                        LogActivityUseCase logActivityUseCase,
                        PermissionService permissionService) {
                this.projectRepository = projectRepository;
                this.taskRepository = taskRepository;
                this.taskMapper = taskMapper;
                this.logActivityUseCase = logActivityUseCase;
                this.permissionService = permissionService;
        }

        @Override
        @Transactional
        public TaskResult createTask(Long projectId, CreateTaskRequest request, String userEmail) {
                log.info("[CreateTask] Bắt đầu - projectId={}", projectId);

                // Rule 1: Project phải tồn tại
                projectRepository.findById(projectId)
                                .orElseThrow(() -> {
                                        log.error("[CreateTask] Project không tồn tại: projectId={}", projectId);
                                        return new IllegalArgumentException("Dự án không tồn tại");
                                });
                log.debug("[CreateTask] Project tồn tại: projectId={}", projectId);

                // Rule 2,3: Validate user tồn tại và là thành viên ACCEPTED của project
                User user = permissionService.validateProjectMember(projectId, userEmail);
                log.debug("[CreateTask] User là thành viên project: userId={}", user.getId());

                // Rule 4: Tính position = Task cuối + 1 (Factory xử lý)
                int currentCount = taskRepository.countByProjectId(projectId);
                log.debug("[CreateTask] Số task hiện tại trong project: {}", currentCount);

                // Rule 5: Tạo Task qua Factory (status=TODO, assigneeId=null, position tự động)
                Task task = TaskFactory.create(request.getTitle(), request.getDescription(), projectId, currentCount);
                log.debug("[CreateTask] Task đã tạo qua Factory: position={}", task.getPosition());

                // Lưu vào Database
                Task savedTask = taskRepository.save(task);
                log.info("[CreateTask] Hoàn thành - taskId={}, projectId={}", 
                        savedTask.getId(), projectId);

                // Ghi log hoạt động (async, không block)
                logActivityUseCase.logActivity(LogActivityRequest.builder()
                        .projectId(projectId)
                        .userId(user.getId())
                        .actionType(ActionType.TASK_CREATED)
                        .entityType(EntityType.TASK)
                        .entityId(savedTask.getId())
                        .description("Created task: " + savedTask.getTitle())
                        .metadata(Map.of("title", savedTask.getTitle()))
                        .build());

                return taskMapper.toTaskResult(savedTask);
        }
}
