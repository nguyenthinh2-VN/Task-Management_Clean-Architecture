# Tài Liệu API - Task Management System

Mọi API yêu cầu Authen đều phải đính kèm Header:
`Authorization: Bearer <JWT_TOKEN>`

---

## 1. Xác thực & Phân quyền (Auth)

### 1.1 Đăng ký tài khoản hệ thống
- **URL**: `POST /api/auth/register`
- **Auth Required**: No
- **Body** (JSON):
```json
{
    "username": "Nguyen Thinh",
    "email": "nguyenthinhh4@gmail.com",
    "password": "password123"
}
```
- **Note**: Tài khoản tạo mặc định `isVerified = false`.

### 1.2 Đăng nhập hệ thống (Mật khẩu)
- **URL**: `POST /api/auth/login`
- **Auth Required**: No
- **Body** (JSON):
```json
{
    "email": "nguyenthinhh4@gmail.com",
    "password": "password123"
}
```

### 1.3 Đăng nhập bằng Google
- **URL**: `POST /api/auth/login/google`
- **Auth Required**: No
- **Body** (JSON):
```json
{
    "idToken": "eyJhbGciOiJSUz..."
}
```
- **Note**: `isVerified` tự động gạt sang `true`.

---

## 2. Quản lý Dự án (Project)

### 2.1 Tạo dự án mới
- **URL**: `POST /api/projects`
- **Auth Required**: Yes
- **Body** (JSON):
```json
{
    "name": "Dự án Thiết kế Website",
    "description": "Làm giao diện chuẩn UI/UX cho công ty ABC"
}
```
- **Note**: Người tạo tự động trở thành `OWNER` và Status `ACCEPTED`.

### 2.2 Xóa dự án
- **URL**: `DELETE /api/projects/{projectId}`
- **Auth Required**: Yes (Yêu cầu phải là `OWNER` của dự án)
- **Response** (200 OK):
```json
{
    "status": 200,
    "message": "Dự án đã được xóa thành công",
    "data": null
}
```
- **Note**: Logic xóa cũng bao gồm việc dọn dẹp (cascade delete) toàn bộ các Công việc (`Tasks`) và Thành viên (`ProjectMembers`) thuộc dự án này để tránh rác CSDL.

---

## 3. Lời mời tham gia Dự án (Invitations)

### 3.1 Mời thành viên vào dự án
- **URL**: `POST /api/projects/{projectId}/invite`
- **Auth Required**: Yes (Cần quyền `OWNER` trong `{projectId}`)
- **Body** (JSON):
```json
{
    "inviteeEmail": "nguoi_duoc_moi@gmail.com"
}
```
- **Note**: Trạng thái lời mời ban đầu là `PENDING`. Người được mời bắt buộc phải verified.

### 3.2 Nhận danh sách Lời mời của TÔI
- **URL**: `GET /api/users/me/invitations`
- **Auth Required**: Yes
- **Response** (Trả về Array Object):
```json
[
    {
        "id": 1,
        "projectId": 10,
        "projectName": "Dự án Thiết kế Website",
        "role": "MEMBER",
        "status": "PENDING"
    }
]
```

### 3.3 Chấp nhận / Từ chối Lời mời
- **URL**: `POST /api/projects/{projectId}/invitations/respond`
- **Auth Required**: Yes
- **Body** (JSON):
```json
{
    "isAccept": true
}
```
- **Note**: `true` để ACCEPTED (vào dự án), `false` để REJECTED (từ chối lòi mời).
