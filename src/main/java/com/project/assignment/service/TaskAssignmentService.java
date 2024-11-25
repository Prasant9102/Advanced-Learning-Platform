package com.project.assignment.service;

import com.project.assignment.dto.TaskAssignmentDTO;
import com.project.assignment.entity.TaskAssignment;

import java.util.List;

public interface TaskAssignmentService {

    TaskAssignment createTaskAssignment(TaskAssignmentDTO taskAssignmentDTO);

    List<TaskAssignment> getAllTaskAssignments();

    TaskAssignment getTaskAssignmentById(long id);

    TaskAssignment updateTaskAssignment(long id, TaskAssignmentDTO taskAssignmentDTO);

    void deleteTaskAssignment(long id);
}
