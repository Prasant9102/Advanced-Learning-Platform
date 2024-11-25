package com.project.assignment;

import com.project.assignment.controller.UserRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AssignmentApplication {

    @Autowired
    private UserRestController userRestController;

    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }

}
