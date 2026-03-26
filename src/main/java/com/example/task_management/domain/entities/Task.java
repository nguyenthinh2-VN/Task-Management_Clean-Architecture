package com.example.task_management.domain.entities;

import com.example.task_management.domain.enums.TaskStatus;

public class Task {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Long projectId;
    private Long assigneeId;
    private Integer position;  // thứ tự hiển thị trong project

    public Task() {}

    // ── Domain Methods (Bảo vệ chuyển đổi trạng thái) ──────────────
    public void start() {
        if (this.status != TaskStatus.TODO) {
            throw new IllegalStateException("Chỉ task đang ở trạng thái TODO mới được bắt đầu (start)");
        }
        this.status = TaskStatus.IN_PROGRESS;
    }

    public void complete() {
        if (this.status != TaskStatus.IN_PROGRESS) {
            throw new IllegalStateException("Task phải ở trạng thái IN_PROGRESS trước khi hoàn thành (complete)");
        }
        this.status = TaskStatus.DONE;
    }

    public void cancel() {
        if (this.status == TaskStatus.DONE) {
            throw new IllegalStateException("Không thể hủy task đã DONE");
        }
        this.status = TaskStatus.CANCELLED;
    }

    // ── Getters & Setters ──────────────────────────────────────────
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() { return status; }
    // Chỉ dùng để set giá trị ban đầu (từ Factory/Mapper), không dùng để thay đổi trạng thái
    // Để chuyển trạng thái: dùng start(), complete(), cancel()
    public void setStatus(TaskStatus status) { this.status = status; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public Long getAssigneeId() { return assigneeId; }
    public void setAssigneeId(Long assigneeId) { this.assigneeId = assigneeId; }

    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }
}
