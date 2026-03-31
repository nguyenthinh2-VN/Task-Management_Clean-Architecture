package com.example.task_management.domain.services;

public interface PermissionService {
    Long validateProjectMember(Long projectId, String email);
}
