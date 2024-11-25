package com.project.assignment.service;

import com.project.assignment.dto.UserDTO;
import com.project.assignment.entity.UserRequest;
import com.project.assignment.entity.UserResponse;
import com.project.assignment.entity.Users;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    Optional<Users> findByUserName(String userName);

    Integer saveUser(Users user);

    UserResponse createUser(Map<String, Object> userPayload, Long createdByUserId);

    UserResponse loginUser(UserRequest request);

    public Optional<Users> findUserByEmail(String email);

    public Optional<Users> findById(Integer userId);

    public Users findUserByResetToken(String resetToken);

    List<UserDTO> getUsersByCourseIdAndStatus(Long courseId, String status);

    UserDTO getUserByUserId(Integer userId);

    public String deleteUserByUserId(Integer userId);

    public UserResponse updateUser(Integer userId, Map<String, Object> userPayload, Long updatedByUserId);
}
