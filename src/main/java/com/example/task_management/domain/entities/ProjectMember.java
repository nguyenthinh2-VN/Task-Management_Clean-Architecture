package com.example.task_management.domain.entities;

import com.example.task_management.domain.enums.MemberRole;
import com.example.task_management.domain.enums.InvitationStatus;

public class ProjectMember {
    private Long id;
    private Long projectId;
    private Long userId;
    private MemberRole role;
    private InvitationStatus invitationStatus;

    public ProjectMember() {
    }

    public boolean isOwnerAccepted() {
        return this.role == MemberRole.OWNER
                && this.invitationStatus == InvitationStatus.ACCEPTED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }

    public InvitationStatus getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(InvitationStatus invitationStatus) {
        this.invitationStatus = invitationStatus;
    }

}
