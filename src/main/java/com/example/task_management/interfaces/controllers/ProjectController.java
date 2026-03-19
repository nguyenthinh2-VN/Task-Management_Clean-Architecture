package com.example.task_management.interfaces.controllers;

import com.example.task_management.application.dto.request.project.*;
import com.example.task_management.application.dto.response.project.*;
import com.example.task_management.application.usecases.project.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// [DI] inject tất cả project use cases qua interface
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;
    private final DeleteProjectUseCase deleteProjectUseCase;
    private final GetProjectListUseCase getProjectListUseCase;
    private final InviteMemberUseCase inviteMemberUseCase;
    private final AcceptInvitationUseCase acceptInvitationUseCase;

    public ProjectController(CreateProjectUseCase createProjectUseCase,
                             UpdateProjectUseCase updateProjectUseCase,
                             DeleteProjectUseCase deleteProjectUseCase,
                             GetProjectListUseCase getProjectListUseCase,
                             InviteMemberUseCase inviteMemberUseCase,
                             AcceptInvitationUseCase acceptInvitationUseCase) {
        this.createProjectUseCase = createProjectUseCase;
        this.updateProjectUseCase = updateProjectUseCase;
        this.deleteProjectUseCase = deleteProjectUseCase;
        this.getProjectListUseCase = getProjectListUseCase;
        this.inviteMemberUseCase = inviteMemberUseCase;
        this.acceptInvitationUseCase = acceptInvitationUseCase;
    }

    // UC03 – Tạo project
    @PostMapping
    public ResponseEntity<ProjectResponse> create(@RequestBody CreateProjectRequest request) {
        return ResponseEntity.ok(createProjectUseCase.createProject(request));
    }

    // UC04 – Cập nhật project
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(@PathVariable Long id, @RequestBody UpdateProjectRequest request) {
        return ResponseEntity.ok(updateProjectUseCase.updateProject(id, request));
    }

    // UC05 – Xóa project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteProjectUseCase.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    // UC06 – Xem danh sách project
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> list(@RequestParam Long ownerId) {
        return ResponseEntity.ok(getProjectListUseCase.getProjectsByOwner(ownerId));
    }

    // UC07 – Mời thành viên
    @PostMapping("/{id}/members")
    public ResponseEntity<ProjectMemberResponse> invite(@PathVariable Long id, @RequestBody InviteMemberRequest request) {
        return ResponseEntity.ok(inviteMemberUseCase.inviteMember(id, request));
    }

    // UC08 – Chấp nhận lời mời
    @PatchMapping("/invitations/{invitationId}/accept")
    public ResponseEntity<ProjectMemberResponse> accept(@PathVariable Long invitationId, @RequestParam Long userId) {
        return ResponseEntity.ok(acceptInvitationUseCase.acceptInvitation(invitationId, userId));
    }
}
