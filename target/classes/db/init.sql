-- ============================================================
-- Task Management System – Database Init Script
-- MySQL 8.x
-- ============================================================

CREATE DATABASE IF NOT EXISTS task_management_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE task_management_db;

-- ============================================================
-- 1. USERS
-- ============================================================
CREATE TABLE IF NOT EXISTS users (
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    username   VARCHAR(50)  NOT NULL,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    INDEX idx_users_email (email)
) ENGINE = InnoDB;

-- ============================================================
-- 2. PROJECTS
-- ============================================================
CREATE TABLE IF NOT EXISTS projects (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    owner_id    BIGINT       NOT NULL,
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    CONSTRAINT fk_projects_owner FOREIGN KEY (owner_id) REFERENCES users (id) ON DELETE CASCADE,
    INDEX idx_projects_owner (owner_id)
) ENGINE = InnoDB;

-- ============================================================
-- 3. PROJECT_MEMBERS
-- ============================================================
CREATE TABLE IF NOT EXISTS project_members (
    id                BIGINT      NOT NULL AUTO_INCREMENT,
    project_id        BIGINT      NOT NULL,
    user_id           BIGINT      NOT NULL,
    role              ENUM('OWNER','MEMBER') NOT NULL DEFAULT 'MEMBER',
    invitation_status ENUM('PENDING','ACCEPTED','REJECTED') NOT NULL DEFAULT 'PENDING',
    created_at        DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    UNIQUE KEY uq_project_member (project_id, user_id),
    CONSTRAINT fk_pm_project FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE,
    CONSTRAINT fk_pm_user    FOREIGN KEY (user_id)    REFERENCES users    (id) ON DELETE CASCADE,
    INDEX idx_pm_project (project_id),
    INDEX idx_pm_user    (user_id)
) ENGINE = InnoDB;

-- ============================================================
-- 4. TASKS
-- ============================================================
CREATE TABLE IF NOT EXISTS tasks (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    title       VARCHAR(200) NOT NULL,
    description TEXT,
    status      ENUM('TODO','IN_PROGRESS','DONE','CANCELLED') NOT NULL DEFAULT 'TODO',
    project_id  BIGINT       NOT NULL,
    assignee_id BIGINT,
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    CONSTRAINT fk_tasks_project  FOREIGN KEY (project_id)  REFERENCES projects (id) ON DELETE CASCADE,
    CONSTRAINT fk_tasks_assignee FOREIGN KEY (assignee_id) REFERENCES users    (id) ON DELETE SET NULL,
    INDEX idx_tasks_project  (project_id),
    INDEX idx_tasks_assignee (assignee_id),
    INDEX idx_tasks_status   (status)
) ENGINE = InnoDB;
