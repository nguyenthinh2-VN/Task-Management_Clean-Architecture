package com.example.task_management.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class VerificationToken {

    private Long id;
    private String token;
    private Long userId;
    private LocalDateTime expiryDate;
    private boolean used;

    public VerificationToken() {
        this.token = UUID.randomUUID().toString();
        this.expiryDate = LocalDateTime.now().plusHours(24); // Token hết hạn sau 24h
        this.used = false;
    }

    public VerificationToken(Long userId) {
        this();
        this.userId = userId;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }

    public boolean isValid() {
        return !used && !isExpired();
    }

    
    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }

    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }

    public void markAsUsed() {
        this.used = true;
    }
}
