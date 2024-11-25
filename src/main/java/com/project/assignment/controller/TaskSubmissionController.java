package com.project.assignment.controller;

import com.project.assignment.dto.TaskSubmissionDTO;
import com.project.assignment.entity.TaskSubmission;
import com.project.assignment.service.TaskSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alp/api/v1/taskSubmissions")
public class TaskSubmissionController {

    @Autowired
    private TaskSubmissionService taskSubmissionService;

    @PostMapping
    public ResponseEntity<TaskSubmission> createTaskSubmission(@RequestBody TaskSubmissionDTO taskSubmissionDTO) {
        TaskSubmission taskSubmission = taskSubmissionService.createTaskSubmission(taskSubmissionDTO);
        return new ResponseEntity<>(taskSubmission, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskSubmission>> getAllTaskSubmissions() {
        List<TaskSubmission> taskSubmissions = taskSubmissionService.getAllTaskSubmissions();
        return new ResponseEntity<>(taskSubmissions, HttpStatus.OK);
    }


    @GetMapping("/{taskSubmissionId}")
    public ResponseEntity<TaskSubmission> getTaskSubmissionById(@PathVariable("taskSubmissionId") long id) {
        // Retrieve task submission by id
        TaskSubmission taskSubmission = taskSubmissionService.getTaskSubmissionById(id);

        // Return 404 if taskSubmission is not found
        if (taskSubmission == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(taskSubmission, HttpStatus.OK);
    }

    @PutMapping("/{taskSubmissionId}")
    public ResponseEntity<TaskSubmission> updateTaskSubmission(
            @PathVariable("taskSubmissionId") long id,
            @RequestBody TaskSubmissionDTO taskSubmissionDTO) {

        // Check if the task submission exists before updating
        TaskSubmission existingTaskSubmission = taskSubmissionService.getTaskSubmissionById(id);
        if (existingTaskSubmission == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if task submission is not found
        }

        // Update the task submission
        TaskSubmission updatedTaskSubmission = taskSubmissionService.updateTaskSubmission(id, taskSubmissionDTO);

        return new ResponseEntity<>(updatedTaskSubmission, HttpStatus.OK);
    }

    @DeleteMapping("/{taskSubmissionId}")
    public ResponseEntity<Void> deleteTaskSubmission(@PathVariable("taskSubmissionId") long id) {
        // Check if the task submission exists before attempting to delete
        TaskSubmission existingTaskSubmission = taskSubmissionService.getTaskSubmissionById(id);
        if (existingTaskSubmission == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if task submission is not found
        }

        // Delete the task submission
        taskSubmissionService.deleteTaskSubmission(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return 204 for successful deletion
    }
}
