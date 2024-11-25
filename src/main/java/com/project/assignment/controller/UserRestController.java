package com.project.assignment.controller;


import com.project.assignment.dto.UserDTO;
import com.project.assignment.entity.*;
import com.project.assignment.exception.UserAlreadyExistException;
import com.project.assignment.service.RefreshTokenService;
import com.project.assignment.service.RoleService;
import com.project.assignment.service.UserService;
import com.project.assignment.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@EnableWebSecurity
@RequestMapping("/alp/api/v1/user")
public class UserRestController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private RoleService roleService;


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody Map<String, Object> userPayload, @RequestHeader HttpHeaders headers) {
        String authKey = headers.get("authorization").toString();
        String token = authKey.replace("Bearer ", "");

        Long createdByUserId = jwtTokenUtil.getUserIdFromToken(token);

        UserResponse response = userService.createUser(userPayload, createdByUserId);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request) {
        UserResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/refresh")
    public UserResponse refreshJwtToken(@RequestBody RefreshTokenRequest request) {
        // Verify the provided refresh token
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
        if (refreshToken == null) {
            throw new UserAlreadyExistException("Invalid refresh token.");
        }

        // Fetch the user associated with the refresh token
        Users user = refreshToken.getUser();
        if (user == null) {
            throw new UserAlreadyExistException("User not found for the provided refresh token.");
        }

        // Fetch the role ID from the User entity
        Long roleId = user.getRoleId().getRoleId();

        // Fetch the role entity using the role ID
        Roles role = roleService.findById(roleId).orElseThrow(() -> new UserAlreadyExistException("Role not found for roleId: " + roleId));

        String roleName = role.getRoleName();

        // Generate a new JWT token with the role information
        Set<String> roles = Collections.singleton(roleName);
        String accessToken = jwtTokenUtil.generateJWTToken(user, roles);

        // Build and return the UserResponse
        return UserResponse.builder().token(accessToken) // Assuming token field exists in UserResponse
                .refreshToken(refreshToken.getRefreshToken()).message("Token refreshed successfully") // Example message
                .build();
    }


    @GetMapping("/course/{courseId}")
    public List<UserDTO> getUsersByCourseId(@PathVariable Long courseId, @RequestHeader HttpHeaders headers) {
        String authKey = headers.get("authorization").toString();
        String token = authKey.replace("Bearer ", "");


        return userService.getUsersByCourseIdAndStatus(courseId, "Active");
    }


    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userID, @RequestHeader HttpHeaders headers) {
        String authKey = headers.get("authorization").toString();
        String token = authKey.replace("Bearer ", "");

        UserDTO userDTO = userService.getUserByUserId(userID);
        return ResponseEntity.ok(userDTO);
    }


    @DeleteMapping("/{userID}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer userID, @RequestHeader HttpHeaders headers) {
        String authKey = headers.get("authorization").toString();
        String token = authKey.replace("Bearer ", "");

        String message = userService.deleteUserByUserId(userID);
        return ResponseEntity.ok(message);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer userId, @RequestBody Map<String, Object> userPayload, @RequestHeader HttpHeaders headers) {
        String authKey = headers.get("authorization").toString();
        String token = authKey.replace("Bearer ", "");

        Long updatedByUserId = jwtTokenUtil.getUserIdFromToken(token);

        UserResponse response = userService.updateUser(userId, userPayload, updatedByUserId);
        return ResponseEntity.ok(response);
    }


}
