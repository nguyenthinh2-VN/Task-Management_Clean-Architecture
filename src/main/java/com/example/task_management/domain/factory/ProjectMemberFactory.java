package com.example.task_management.domain.factory;

import com.example.task_management.domain.entities.ProjectMember;
import com.example.task_management.domain.enums.InvitationStatus;
import com.example.task_management.domain.enums.MemberRole;

public class ProjectMemberFactory {

    public static ProjectMember createOwner(Long projectId, Long userId) {
        ProjectMember member = new ProjectMember();
        member.setProjectId(projectId);
        member.setUserId(userId);
        member.setRole(MemberRole.OWNER);
        member.setInvitationStatus(InvitationStatus.ACCEPTED);

        return member;
    }

    public static ProjectMember createInvite(Long projectId, Long userId) {
        ProjectMember member = new ProjectMember();
        member.setProjectId(projectId);
        member.setUserId(userId);
        member.setRole(MemberRole.MEMBER);
        member.setInvitationStatus(InvitationStatus.PENDING);
        return member;
    }
}