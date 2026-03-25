package com.example.task_management.application.usecases.impl.project;

import com.example.task_management.application.dto.request.project.InviteMemberRequest;
import com.example.task_management.application.repositories.ProjectMemberRepository;
import com.example.task_management.application.repositories.ProjectRepository;
import com.example.task_management.application.repositories.UserRepository;
import com.example.task_management.application.usecases.project.InviteMemberUseCase;
import com.example.task_management.domain.entities.Project;
import com.example.task_management.domain.entities.ProjectMember;
import com.example.task_management.domain.entities.User;
import com.example.task_management.domain.enums.InvitationStatus;
import com.example.task_management.domain.enums.MemberRole;
import com.example.task_management.domain.factory.ProjectMemberFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InviteMemberUseCaseImpl implements InviteMemberUseCase {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;

    public InviteMemberUseCaseImpl(ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository,
            UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void inviteMember(Long projectId, InviteMemberRequest request, String inviterEmail) {
        // 1. Inviter
        User inviter = userRepository.findByEmail(inviterEmail)
                .orElseThrow(() -> new IllegalArgumentException("Người mời không tồn tại"));

        // 2. Project
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Dự án không tồn tại"));

        // 3. Membership
        ProjectMember inviterMembership = projectMemberRepository
                .findByProjectIdAndUserId(projectId, inviter.getId())
                .orElseThrow(() -> new IllegalArgumentException("Bạn không thuộc dự án"));

        if (!inviterMembership.isOwnerAccepted()) {
            throw new IllegalArgumentException("Chỉ OWNER mới được mời");
        }

        // 4. Validate invitee
        if (inviterEmail.equalsIgnoreCase(request.getInviteeEmail())) {
            throw new IllegalArgumentException("Không thể tự mời chính mình");
        }

        User invitee = userRepository.findByEmail(request.getInviteeEmail())
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));

        if (!invitee.isVerified()) {
            throw new IllegalArgumentException("User chưa verify");
        }

        // 5. Check duplicate
        if (projectMemberRepository.findByProjectIdAndUserId(projectId, invitee.getId()).isPresent()) {
            throw new IllegalArgumentException("User đã ở trong project hoặc đã được mời");
        }

        // 6. Tạo Object ProjectMember thông qua Factory
        ProjectMember newMember = ProjectMemberFactory.createInvite(
                projectId,
                invitee.getId());

        projectMemberRepository.save(newMember);

        // 7. (Placeholder) - Tích hợp gọi Email Service tại đây (hiện đang bảo trì)
        System.out.println(
                ">>> Đã gửi Email lời mời tham gia dự án " + project.getName() + " tới địa chỉ: " + invitee.getEmail());
    }
}
