package com.example.task_management.application.usecases.impl.project;

import com.example.task_management.application.dto.request.project.CreateProjectRequest;
import com.example.task_management.application.dto.response.project.ProjectResponse;
import com.example.task_management.application.repositories.ProjectMemberRepository;
import com.example.task_management.application.repositories.ProjectRepository;
import com.example.task_management.application.repositories.UserRepository;
import com.example.task_management.application.usecases.project.CreateProjectUseCase;
import com.example.task_management.domain.entities.Project;
import com.example.task_management.domain.entities.ProjectMember;
import com.example.task_management.domain.entities.User;
import com.example.task_management.domain.enums.InvitationStatus;
import com.example.task_management.domain.enums.MemberRole;
import com.example.task_management.interfaces.mappers.ProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateProjectUseCaseImpl implements CreateProjectUseCase {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;

    public CreateProjectUseCaseImpl(
            ProjectRepository projectRepository,
            ProjectMemberRepository projectMemberRepository,
            UserRepository userRepository,
            ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.userRepository = userRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    @Transactional
    public ProjectResponse createProject(CreateProjectRequest request, String currentUserEmail) {
        // 1. Lấy thông tin User hiện tại từ Database thông qua Email lấy từ SecurityContext
        User owner = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng hiện tại trong hệ thống."));

        // 2. Khởi tạo đối tượng Project theo đúng Request
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setOwnerId(owner.getId());

        // Lưu Project vào Database
        Project savedProject = projectRepository.save(project);

        // 3. Tự động thêm User tạo dự án thành Thành viên có quyền cao nhất (OWNER) của dự án
        ProjectMember member = new ProjectMember();
        member.setProjectId(savedProject.getId());
        member.setUserId(owner.getId());
        member.setRole(MemberRole.OWNER);
        member.setInvitationStatus(InvitationStatus.ACCEPTED); // Chủ dự án đương nhiên tự "Chấp nhận"

        // Lưu bản ghi phân quyền dự án
        projectMemberRepository.save(member);

        // 4. Trả về Response DTO ra ngoài thông qua Mapper
        return projectMapper.toProjectResponse(savedProject);
    }
}
