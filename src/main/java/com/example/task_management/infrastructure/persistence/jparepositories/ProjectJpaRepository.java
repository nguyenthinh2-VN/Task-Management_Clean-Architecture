package com.example.task_management.infrastructure.persistence.jparepositories;

import com.example.task_management.infrastructure.persistence.jpaentities.ProjectJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpaEntity, Long> {
    List<ProjectJpaEntity> findAllByOwnerId(Long ownerId);
}
