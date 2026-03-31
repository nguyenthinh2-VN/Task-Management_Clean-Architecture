package com.example.task_management.application.usecases.project;

import com.example.task_management.interfaces.dto.request.project.RespondInvitationRequest;

public interface RespondInvitationUseCase {

    /**
     * Đồng ý hoặc Từ chối Lời mời vào Project
     * 
     * @param projectId ID của dự án người dùng đang respond
     * @param request Cờ boolean { isAccept } (true=Chấp nhận, false=Từ chối)
     * @param userEmail Của người được mời (Invitee)
     */
    void respondInvitation(Long projectId, RespondInvitationRequest request, String userEmail);

}
