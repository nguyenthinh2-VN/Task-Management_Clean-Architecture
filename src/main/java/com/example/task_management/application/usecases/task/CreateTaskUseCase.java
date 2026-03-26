package com.example.task_management.application.usecases.task;

import com.example.task_management.application.dto.request.task.CreateTaskRequest;
import com.example.task_management.application.dto.response.task.TaskResponse;

public interface CreateTaskUseCase {

    /**
     * Tạo Task mới trong Project
     *
     * @param projectId  ID của dự án chứa Task
     * @param request    Payload: title, description
     * @param userEmail  Email người dùng đang login (lấy từ JWT)
     * @return TaskResponse chứa thông tin Task đã tạo
     */
    TaskResponse createTask(Long projectId, CreateTaskRequest request, String userEmail);

}
