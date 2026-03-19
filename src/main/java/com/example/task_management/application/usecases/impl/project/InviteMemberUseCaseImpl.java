package com.example.task_management.application.usecases.impl.project;

import com.example.task_management.application.dto.request.project.InviteMemberRequest;
import com.example.task_management.application.dto.response.project.ProjectMemberResponse;
import com.example.task_management.application.repositories.ProjectMemberRepository;
import com.example.task_management.application.usecases.project.InviteMemberUseCase;
import org.springframework.stereotype.Service;

@Service
public class InviteMemberUseCaseImpl implements InviteMemberUseCase {

    private final ProjectMemberRepository projectMemberRepository;

    public InviteMemberUseCaseImpl(ProjectMemberRepository projectMemberRepository) {
        this.projectMemberRepository = projectMemberRepository;
    }

    @Override
    public ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request) {
        // TODO: implement logic
        return null;
    }
}
