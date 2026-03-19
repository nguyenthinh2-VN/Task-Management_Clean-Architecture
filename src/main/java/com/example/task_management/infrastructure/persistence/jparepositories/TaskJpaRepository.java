package com.example.task_management.infrastructure.persistence.jparepositories;

import com.example.task_management.infrastructure.persistence.jpaentities.TaskJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskJpaRepository extends JpaRepository<TaskJpaEntity, Long> {
    List<TaskJpaEntity> findAllByProjectId(Long projectId);
}
