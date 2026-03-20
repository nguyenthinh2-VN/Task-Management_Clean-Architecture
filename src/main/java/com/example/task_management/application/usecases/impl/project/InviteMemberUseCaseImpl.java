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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InviteMemberUseCaseImpl implements InviteMemberUseCase {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;

    public InviteMemberUseCaseImpl(ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void inviteMember(Long projectId, InviteMemberRequest request, String inviterEmail) {
        // 1. Kiểm tra người đi mời (Inviter)
        User inviter = userRepository.findByEmail(inviterEmail)
                .orElseThrow(() -> new IllegalArgumentException("Người mời không tồn tại"));

        // 2. Dự án phải tồn tại
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Dự án không tồn tại hoặc đã bị xóa"));

        // 3. Quyền hạn của người đi mời (Phải là OWNER và đã ACCEPTED)
        ProjectMember inviterMembership = projectMemberRepository.findByProjectIdAndUserId(projectId, inviter.getId())
                .orElseThrow(() -> new IllegalArgumentException("Bạn không có mặt trong dự án này"));

        if (inviterMembership.getRole() != MemberRole.OWNER || inviterMembership.getInvitationStatus() != InvitationStatus.ACCEPTED) {
            throw new IllegalArgumentException("Chỉ chủ sở hữu (OWNER) mới có quyền mời thành viên khác");
        }

        // 4. Kiểm tra người được mời (Invitee)
        if (inviterEmail.equalsIgnoreCase(request.getInviteeEmail())) {
            throw new IllegalArgumentException("Bạn không thể tự mời chính mình");
        }

        User invitee = userRepository.findByEmail(request.getInviteeEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email người được mời không tồn tại trên hệ thống"));

        if (!invitee.isVerified()) {
            throw new IllegalArgumentException("Không thể mời người dùng chưa được xác thực tài khoản (chưa verified)");
        }

        // 5. Kiểm tra chồng chéo (Đã mời hoặc đã vào dự án chưa?)
        Optional<ProjectMember> existingMember = projectMemberRepository.findByProjectIdAndUserId(projectId, invitee.getId());
        if (existingMember.isPresent()) {
            throw new IllegalArgumentException("Người này đã nằm trong dự án hoặc đã có một lời mời đang chờ xử lý");
        }

        // 6. Ghi nhận lời mời vào Database (In-app Notification)
        ProjectMember newMember = new ProjectMember();
        newMember.setProjectId(projectId);
        newMember.setUserId(invitee.getId());
        newMember.setRole(MemberRole.MEMBER); // Mặc định chỉ cho quyền MEMBER
        newMember.setInvitationStatus(InvitationStatus.PENDING); // Trạng thái chờ

        projectMemberRepository.save(newMember);

        // 7. (Placeholder) - Tích hợp gọi Email Service tại đây (hiện đang bảo trì)
        System.out.println(">>> Đã gửi Email lời mời tham gia dự án " + project.getName() + " tới địa chỉ: " + invitee.getEmail());
    }
}
