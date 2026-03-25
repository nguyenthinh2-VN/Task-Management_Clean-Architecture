package com.example.task_management.application.usecases.impl.project;

import com.example.task_management.application.repositories.ProjectMemberRepository;
import com.example.task_management.application.repositories.ProjectRepository;
import com.example.task_management.application.repositories.TaskRepository;
import com.example.task_management.application.repositories.UserRepository;
import com.example.task_management.application.usecases.project.DeleteProjectUseCase;
import com.example.task_management.domain.entities.Project;
import com.example.task_management.domain.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteProjectUseCaseImpl implements DeleteProjectUseCase {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public DeleteProjectUseCaseImpl(
            ProjectRepository projectRepository,
            ProjectMemberRepository projectMemberRepository,
            TaskRepository taskRepository,
            UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void deleteProject(Long projectId, String currentUserEmail) {
        // 1. Xác minh User hiện tại
        User user = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng hiện tại trong hệ thống."));

        // 2. Tìm kiếm dự án cần xóa
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Dự án không tồn tại."));

        // 3. Kiểm tra quyền sở hữu (Chỉ Owner mới có quyền xóa)
        if (!project.getOwnerId().equals(user.getId())) {
            throw new IllegalArgumentException("Bạn không có quyền xóa dự án này do không phải là Owner.");
        }

        // 4. Xóa tuần tự Tasks, ProjectMembers, và Project
        taskRepository.deleteAllByProjectId(projectId);
        projectMemberRepository.deleteAllByProjectId(projectId);
        projectRepository.deleteById(projectId);
    }
}
