package com.example.task_management.infrastructure.persistence.adapters;

import com.example.task_management.application.repositories.VerificationTokenRepository;
import com.example.task_management.domain.entities.VerificationToken;
import com.example.task_management.infrastructure.persistence.jpaentities.VerificationTokenJpaEntity;
import com.example.task_management.infrastructure.persistence.jparepositories.VerificationTokenJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VerificationTokenRepositoryAdapter implements VerificationTokenRepository {

    private final VerificationTokenJpaRepository jpaRepository;

    public VerificationTokenRepositoryAdapter(VerificationTokenJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public VerificationToken save(VerificationToken token) {
        VerificationTokenJpaEntity entity = toJpaEntity(token);
        VerificationTokenJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return jpaRepository.findByToken(token).map(this::toDomain);
    }

    @Override
    public Optional<VerificationToken> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId).map(this::toDomain);
    }

    @Override
    public void deleteByUserId(Long userId) {
        jpaRepository.deleteByUserId(userId);
    }

    @Override
    public void delete(VerificationToken token) {
        jpaRepository.deleteById(token.getId());
    }

    // ── Mappers ────────────────────────────────────────────────────
    private VerificationTokenJpaEntity toJpaEntity(VerificationToken token) {
        return VerificationTokenJpaEntity.builder()
                .id(token.getId())
                .token(token.getToken())
                .userId(token.getUserId())
                .expiryDate(token.getExpiryDate())
                .used(token.isUsed())
                .build();
    }

    private VerificationToken toDomain(VerificationTokenJpaEntity entity) {
        VerificationToken token = new VerificationToken();
        token.setId(entity.getId());
        token.setToken(entity.getToken());
        token.setUserId(entity.getUserId());
        token.setExpiryDate(entity.getExpiryDate());
        token.setUsed(entity.isUsed());
        return token;
    }
}
