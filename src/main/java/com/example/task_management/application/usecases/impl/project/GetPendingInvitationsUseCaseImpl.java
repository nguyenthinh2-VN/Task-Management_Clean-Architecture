package com.example.task_management.application.usecases.impl.project;

import com.example.task_management.application.dto.response.project.InvitationResponse;
import com.example.task_management.application.repositories.ProjectMemberRepository;
import com.example.task_management.application.repositories.ProjectRepository;
import com.example.task_management.application.repositories.UserRepository;
import com.example.task_management.application.usecases.project.GetPendingInvitationsUseCase;
import com.example.task_management.domain.entities.Project;
import com.example.task_management.domain.entities.ProjectMember;
import com.example.task_management.domain.entities.User;
import com.example.task_management.domain.enums.InvitationStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetPendingInvitationsUseCaseImpl implements GetPendingInvitationsUseCase {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public GetPendingInvitationsUseCaseImpl(
            ProjectMemberRepository projectMemberRepository, 
            ProjectRepository projectRepository, 
            UserRepository userRepository) {
        this.projectMemberRepository = projectMemberRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<InvitationResponse> getPendingInvitations(String userEmail) {
        // 1. Lấy thông tin người gọi API
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));

        // 2. Chọc Data tầng DB lấy danh sách PENDING members
        List<ProjectMember> pendingMemberships = projectMemberRepository
                .findAllByUserIdAndInvitationStatus(user.getId(), InvitationStatus.PENDING);

        // 3. Mapping từ Domain sang DTO
        List<InvitationResponse> responses = new ArrayList<>();
        for (ProjectMember membership : pendingMemberships) {
            
            // Query lấy Tên Dự án tương ứng cho List (Vì ProjectMember hiện chưa chứa Tên dự án)
            String projectName = projectRepository.findById(membership.getProjectId())
                    .map(Project::getName)
                    .orElse("Dự án không xác định");

            responses.add(InvitationResponse.builder()
                    .id(membership.getId())
                    .projectId(membership.getProjectId())
                    .projectName(projectName)
                    .role(membership.getRole())
                    .status(membership.getInvitationStatus())
                    .build());
        }

        return responses;
    }
}
