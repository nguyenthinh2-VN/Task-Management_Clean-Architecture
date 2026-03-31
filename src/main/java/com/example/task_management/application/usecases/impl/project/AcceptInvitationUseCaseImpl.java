package com.example.task_management.application.usecases.impl.project;

import com.example.task_management.application.DTOUsecase.response.project.ProjectMemberResult;
import com.example.task_management.application.repositories.ProjectMemberRepository;
import com.example.task_management.application.usecases.project.AcceptInvitationUseCase;
import org.springframework.stereotype.Service;

@Service
public class AcceptInvitationUseCaseImpl implements AcceptInvitationUseCase {

    private final ProjectMemberRepository projectMemberRepository;

    public AcceptInvitationUseCaseImpl(ProjectMemberRepository projectMemberRepository) {
        this.projectMemberRepository = projectMemberRepository;
    }

    @Override
    public ProjectMemberResult acceptInvitation(Long invitationId, Long userId) {
        // TODO: implement logic
        return null;
    }
}
