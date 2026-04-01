package com.example.task_management.infrastructure.persistence.jparepositories;

import com.example.task_management.infrastructure.persistence.jpaentities.VerificationTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenJpaRepository extends JpaRepository<VerificationTokenJpaEntity, Long> {
    Optional<VerificationTokenJpaEntity> findByToken(String token);
    Optional<VerificationTokenJpaEntity> findByUserId(Long userId);
    Optional<VerificationTokenJpaEntity> findTopByUserIdOrderByCreatedAtDesc(Long userId);
    void deleteByUserId(Long userId);
}
