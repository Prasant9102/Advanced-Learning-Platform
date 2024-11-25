package com.project.assignment.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserResponse {

    private String token;

    private String refreshToken;


    private String message;

    private Integer userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private String roleName; // Assuming role name as a string
    private Long roleId;
    private Long courseId;
    private String courseName;
    private String createdBy;
    private Timestamp createdDate;
    private String updatedBy;
    private Timestamp updatedDate;
    private Timestamp lastLoggedInTime;
    private Map<String, List<String>> entitlements;

    public UserResponse(Integer userId, String userName, String firstName, String lastName, String email, String status, String roleName, Long roleId, Long courseId, String message, String courseName, String createdBy, Timestamp createdDate, String updatedBy, Timestamp updatedDate, Timestamp lastLoggedInTime) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.roleName = roleName;
        this.roleId = roleId;
        this.courseId = courseId;
        this.message = message;
        this.courseName = courseName;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.lastLoggedInTime = lastLoggedInTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
