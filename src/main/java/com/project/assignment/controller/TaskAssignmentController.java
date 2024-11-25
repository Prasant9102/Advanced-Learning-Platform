package com.project.assignment.controller;

import com.project.assignment.dto.TaskAssignmentDTO;
import com.project.assignment.entity.TaskAssignment;
import com.project.assignment.service.TaskAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alp/api/v1/taskAssignments")
public class TaskAssignmentController {

    @Autowired
    private TaskAssignmentService taskAssignmentService;

    @PostMapping
    public ResponseEntity<TaskAssignment> createTaskAssignment(@RequestBody TaskAssignmentDTO taskAssignmentDTO) {
        TaskAssignment taskAssignment = taskAssignmentService.createTaskAssignment(taskAssignmentDTO);
        return new ResponseEntity<>(taskAssignment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskAssignment>> getAllTaskAssignments() {
        List<TaskAssignment> taskAssignments = taskAssignmentService.getAllTaskAssignments();
        return new ResponseEntity<>(taskAssignments, HttpStatus.OK);
    }

    @GetMapping("/{taskAssignmentId}")
    public ResponseEntity<TaskAssignment> getTaskAssignmentById(@PathVariable("taskAssignmentId") long taskAssignmentId) {
        TaskAssignment taskAssignment = taskAssignmentService.getTaskAssignmentById(taskAssignmentId);
        return new ResponseEntity<>(taskAssignment, HttpStatus.OK);
    }

    @PutMapping("/{taskAssignmentId}")
    public ResponseEntity<TaskAssignment> updateTaskAssignment(
            @PathVariable("taskAssignmentId") long taskAssignmentId,
            @RequestBody TaskAssignmentDTO taskAssignmentDTO) {
        TaskAssignment updatedTaskAssignment = taskAssignmentService.updateTaskAssignment(taskAssignmentId, taskAssignmentDTO);
        return new ResponseEntity<>(updatedTaskAssignment, HttpStatus.OK);
    }

    @DeleteMapping("/{taskAssignmentId}")
    public ResponseEntity<Void> deleteTaskAssignment(@PathVariable("taskAssignmentId") long taskAssignmentId) {
        taskAssignmentService.deleteTaskAssignment(taskAssignmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
