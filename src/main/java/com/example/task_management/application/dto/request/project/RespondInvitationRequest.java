package com.example.task_management.application.dto.request.project;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespondInvitationRequest {
    
    @NotNull(message = "isAccept không được Null. Truyền true để Chấp nhận, false để Từ chối.")
    private Boolean isAccept;
    
}
