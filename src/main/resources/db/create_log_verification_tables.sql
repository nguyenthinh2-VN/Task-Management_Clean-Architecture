-- Bảng log đăng nhập (ghi lại mỗi lần user đăng nhập thành công)
CREATE TABLE IF NOT EXISTS login_logs (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT       NOT NULL,
    ip_address VARCHAR(45),
    login_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    method     VARCHAR(20)  NOT NULL DEFAULT 'PASSWORD', -- PASSWORD | GOOGLE
    CONSTRAINT fk_login_logs_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Bảng xác minh email (mã OTP gửi khi đăng nhập lần đầu)
CREATE TABLE IF NOT EXISTS email_verifications (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT      NOT NULL,
    token        VARCHAR(255) NOT NULL,                  -- UUID / OTP code
    expires_at   DATETIME     NOT NULL,
    verified_at  DATETIME     NULL DEFAULT NULL,         -- NULL = chưa xác minh
    CONSTRAINT fk_email_verif_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
