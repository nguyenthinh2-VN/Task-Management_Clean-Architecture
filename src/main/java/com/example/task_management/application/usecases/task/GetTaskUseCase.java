package com.example.task_management.application.usecases.task;

import com.example.task_management.application.dto.response.task.TaskResponse;
import java.util.List;

public interface GetTaskUseCase {

    /**
     * Lấy danh sách Task trong dự án, có thể lọc theo status
     *
     * @param projectId  ID của dự án
     * @param status     Filter theo status (null = lấy tất cả)
     * @param userEmail  Email người dùng đang login
     * @return Danh sách TaskResponse, sắp xếp theo position tăng dần
     */
    List<TaskResponse> getTasks(Long projectId, String status, String userEmail);

}
