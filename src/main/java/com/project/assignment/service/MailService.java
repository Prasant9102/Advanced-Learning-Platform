package com.project.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }


    public void sendTemplatedEmail(String to, String subject, String firstName, String username, String password, String loginLink) {
        String template = "Hi %s,\n\nYour account is created successfully.\nYou can login to the Advanced Learning Platform portal with the following credentials:\n\n"
                + "Username: %s\nPassword: %s\n\n"
                + "Login here: %s\n\n"
                + "Thank You.\n\n"
                + "If you have any questions or need support, please contact support@prasant.com.\n\n";
        String text = String.format(template, firstName, username, password, loginLink);
        sendEmail(to, subject, text);
    }
}
