package com.project.assignment.controller;

import com.project.assignment.entity.ResetRequest;
import com.project.assignment.entity.Users;
import com.project.assignment.exception.UserAlreadyExistException;
import com.project.assignment.repository.UserRepository;
import com.project.assignment.service.EmailService;
import com.project.assignment.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/password")
public class PasswordController {


    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/forgot/{email}")
    public ResponseEntity<Users> processForgotPasswordForm(@PathVariable("email") String userEmail,
                                                           HttpServletRequest request) {

        System.out.println("in forgot");

        if (userEmail == null || userEmail.isEmpty()) {
            throw new UserAlreadyExistException("Email cannot be null or empty");
        }

        Optional<Users> optional = userService.findUserByEmail(userEmail);
        if (optional.isEmpty()) {
            throw new UserAlreadyExistException("Provided Email ID '" + userEmail + "' is Not Associated with any Client");
        }

        if (!optional.isPresent()) {
            return ResponseEntity.ok(new Users());
        } else {
            Users user = optional.get();
            user.setResetToken(UUID.randomUUID().toString());
            userService.saveUser(user);
            String appUrl = request.getScheme() + "://" + request.getServerName();
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl + ":4200/password-change?token="
                    + user.getResetToken() + "&email=" + user.getEmail());
            emailService.sendEmail(passwordResetEmail);
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("/reset")
    public String setNewPassword(@RequestBody ResetRequest resetRequest) {

        String resetToken = resetRequest.getToken();
        String newPassword = resetRequest.getPassword();

        // Check if reset token or password is null
        if (resetRequest.getToken() == null || resetRequest.getToken().isEmpty() || resetRequest.getPassword() == null || resetRequest.getPassword().isEmpty()) {
            throw new UserAlreadyExistException("Reset token or password cannot be null or empty.");
        }

        Users user = userService.findUserByResetToken(resetToken);
        if (user != null) {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            user.setResetToken(resetToken);
            userRepo.save(user);
            return "You Have Changed Your Password Successfully.";
        } else {
            return "Invalid or expired reset token.";
        }
    }

}
