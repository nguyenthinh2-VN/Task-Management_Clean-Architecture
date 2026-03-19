package com.example.task_management.infrastructure.persistence.adapters;

import com.example.task_management.application.repositories.ProjectMemberRepository;
import com.example.task_management.domain.entities.ProjectMember;
import com.example.task_management.domain.enums.InvitationStatus;
import com.example.task_management.infrastructure.persistence.jparepositories.ProjectMemberJpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ProjectMemberRepositoryAdapter implements ProjectMemberRepository {

    private final ProjectMemberJpaRepository projectMemberJpaRepository;

    public ProjectMemberRepositoryAdapter(ProjectMemberJpaRepository projectMemberJpaRepository) {
        this.projectMemberJpaRepository = projectMemberJpaRepository;
    }

    @Override
    public ProjectMember save(ProjectMember projectMember) {
        // TODO: map domain → jpa, save, map back
        return null;
    }

    @Override
    public Optional<ProjectMember> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ProjectMember> findByProjectIdAndUserId(Long projectId, Long userId) {
        return Optional.empty();
    }

    @Override
    public List<ProjectMember> findAllByProjectId(Long projectId) {
        return List.of();
    }

    @Override
    public List<ProjectMember> findAllByUserIdAndInvitationStatus(Long userId, InvitationStatus status) {
        return List.of();
    }
}
