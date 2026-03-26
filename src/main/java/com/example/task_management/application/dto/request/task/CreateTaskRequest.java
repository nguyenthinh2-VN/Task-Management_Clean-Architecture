package com.example.task_management.application.dto.request.task;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {

    @NotBlank(message = "Tiêu đề task không được để trống")
    private String title;

    private String description; // Optional

}
