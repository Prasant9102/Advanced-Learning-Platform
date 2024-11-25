package com.project.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private String roleName;
    private Long roleId;
    private String courseName;
    private Long courseId;
    private String lastLoggedInTime;
    private String createdBy;
    private String createdDate;
    private String updatedBy;
    private String updatedDate;


}
