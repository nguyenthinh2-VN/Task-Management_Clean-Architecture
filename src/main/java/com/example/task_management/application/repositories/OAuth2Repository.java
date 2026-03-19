package com.example.task_management.application.repositories;

import com.example.task_management.application.dto.response.auth.GoogleUserInfo;

public interface OAuth2Repository {
    
    /**
     * Xác thực và giải mã Google ID Token được gửi từ Frontend
     * 
     * @param idToken Chuỗi JWT do Google cấp cho Frontend
     * @return Thông tin user lấy được từ token
     */
    GoogleUserInfo verifyGoogleIdToken(String idToken);
    
}
