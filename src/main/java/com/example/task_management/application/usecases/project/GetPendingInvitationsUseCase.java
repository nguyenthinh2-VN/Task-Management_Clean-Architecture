package com.example.task_management.application.usecases.project;

import com.example.task_management.application.dto.response.project.InvitationResponse;
import java.util.List;

public interface GetPendingInvitationsUseCase {
    
    /**
     * Lấy danh sách các Lời mời đang chờ xử lý của người dùng hiện tại
     * 
     * @param userEmail Email người dùng đang login
     * @return Danh sách DTO chứa lời mời
     */
    List<InvitationResponse> getPendingInvitations(String userEmail);
    
}
