package com.example.task_management.interfaces.dto.response.project;

import com.example.task_management.domain.enums.InvitationStatus;
import com.example.task_management.domain.enums.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvitationResponse {
    private Long id;                  // ID Của ProjectMember bản ghi
    private Long projectId;           // Truy vết dự án nào
    private String projectName;       // Cần join bảng để lấy tên hiển thị cho đẹp giao diện
    private MemberRole role;
    private InvitationStatus status;
}
