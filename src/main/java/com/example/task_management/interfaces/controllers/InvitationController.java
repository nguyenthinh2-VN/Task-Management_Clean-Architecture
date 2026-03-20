package com.example.task_management.interfaces.controllers;

import com.example.task_management.application.dto.request.project.InviteMemberRequest;
import com.example.task_management.application.dto.request.project.RespondInvitationRequest;
import com.example.task_management.application.dto.response.ApiResponse;
import com.example.task_management.application.dto.response.project.InvitationResponse;
import com.example.task_management.application.usecases.project.GetPendingInvitationsUseCase;
import com.example.task_management.application.usecases.project.InviteMemberUseCase;
import com.example.task_management.application.usecases.project.RespondInvitationUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvitationController {

    private final InviteMemberUseCase inviteMemberUseCase;
    private final GetPendingInvitationsUseCase getPendingInvitationsUseCase;
    private final RespondInvitationUseCase respondInvitationUseCase;

    public InvitationController(
            InviteMemberUseCase inviteMemberUseCase, 
            GetPendingInvitationsUseCase getPendingInvitationsUseCase, 
            RespondInvitationUseCase respondInvitationUseCase) {
        this.inviteMemberUseCase = inviteMemberUseCase;
        this.getPendingInvitationsUseCase = getPendingInvitationsUseCase;
        this.respondInvitationUseCase = respondInvitationUseCase;
    }

    // 1. API - Gửi lời mời (Dành cho Chủ Dự án)
    @PostMapping("/projects/{projectId}/invite")
    public ResponseEntity<ApiResponse<Void>> inviteMember(
            @PathVariable Long projectId,
            @Valid @RequestBody InviteMemberRequest request,
            Authentication authentication) {
        
        inviteMemberUseCase.inviteMember(projectId, request, authentication.getName());
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Gửi lời mời thành công", null));
    }

    // 2. API - Lấy danh sách Lời mời chưa giải quyết (Dành cho người được mời)
    @GetMapping("/users/me/invitations")
    public ResponseEntity<ApiResponse<List<InvitationResponse>>> getMyPendingInvitations(Authentication authentication) {
        
        List<InvitationResponse> pendingInvitations = getPendingInvitationsUseCase.getPendingInvitations(authentication.getName());
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Lấy danh sách lời mời thành công", pendingInvitations));
    }

    // 3. API - Quyết định Chấp nhận/Từ chối Lời mời (Dành cho người được mời)
    @PostMapping("/projects/{projectId}/invitations/respond")
    public ResponseEntity<ApiResponse<Void>> respondInvitation(
            @PathVariable Long projectId,
            @Valid @RequestBody RespondInvitationRequest request,
            Authentication authentication) {
        
        respondInvitationUseCase.respondInvitation(projectId, request, authentication.getName());
        String action = Boolean.TRUE.equals(request.getIsAccept()) ? "Chấp nhận" : "Từ chối";
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Đã " + action + " lời mời vào dự án", null));
    }
}
