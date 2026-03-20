package com.example.task_management.application.usecases.project;

import com.example.task_management.application.dto.request.project.InviteMemberRequest;

public interface InviteMemberUseCase {
    
    /**
     * Gửi lời mời thành viên tham gia dự án
     * 
     * @param projectId ID của dự án
     * @param request Payload chứa email của người được mời
     * @param inviterEmail Email của người login (người đi mời)
     */
    void inviteMember(Long projectId, InviteMemberRequest request, String inviterEmail);
    
}
