package com.example.task_management.application.repositories;

import com.example.task_management.domain.entities.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository {

    VerificationToken save(VerificationToken token);

    Optional<VerificationToken> findByToken(String token);

    Optional<VerificationToken> findByUserId(Long userId);

    void deleteByUserId(Long userId);

    void delete(VerificationToken token);
}
