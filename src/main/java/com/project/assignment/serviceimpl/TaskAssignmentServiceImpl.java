package com.project.assignment.serviceimpl;

import com.project.assignment.dto.TaskAssignmentDTO;
import com.project.assignment.entity.TaskAssignment;
import com.project.assignment.entity.Technologies;
import com.project.assignment.entity.Users;
import com.project.assignment.repository.TaskAssignmentRepository;
import com.project.assignment.repository.TechnologyRepository;
import com.project.assignment.repository.UserRepository;
import com.project.assignment.service.TaskAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskAssignmentServiceImpl implements TaskAssignmentService {

    @Autowired
    private TaskAssignmentRepository taskAssignmentRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Task Assignment
    @Override
    public TaskAssignment createTaskAssignment(TaskAssignmentDTO taskAssignmentDTO) {
        TaskAssignment taskAssignment = new TaskAssignment();
        taskAssignment.setTaskName(taskAssignmentDTO.getTaskName());
        taskAssignment.setStartDate(taskAssignmentDTO.getStartDate());
        taskAssignment.setEndDate(taskAssignmentDTO.getEndDate());
        taskAssignment.setDescription(taskAssignmentDTO.getDescription());
        taskAssignment.setCreatedBy(taskAssignmentDTO.getCreatedBy());
        taskAssignment.setCreatedDate(taskAssignmentDTO.getCreatedDate());
        taskAssignment.setUpdatedBy(taskAssignmentDTO.getUpdatedBy());
        taskAssignment.setUpdatedDate(taskAssignmentDTO.getUpdatedDate());

        Technologies technology = technologyRepository.findById(taskAssignmentDTO.getTechnologyId())
                .orElseThrow(() -> new RuntimeException("Technology not found with ID: " + taskAssignmentDTO.getTechnologyId()));
        taskAssignment.setTechnologies(technology);

        List<Users> users = taskAssignmentDTO.getUserIds().stream()
                .map(userId -> userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId)))
                .collect(Collectors.toList());
        taskAssignment.setTaskAssigneeUsers(users);

        return taskAssignmentRepository.save(taskAssignment);
    }

    // Get All Task Assignments
    @Override
    public List<TaskAssignment> getAllTaskAssignments() {
        return taskAssignmentRepository.findAll();
    }

    // Get Task Assignment by ID
    @Override
    public TaskAssignment getTaskAssignmentById(long id) {
        return taskAssignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task Assignment not found with ID: " + id));
    }

    // Update Task Assignment
    @Override
    public TaskAssignment updateTaskAssignment(long id, TaskAssignmentDTO taskAssignmentDTO) {
        TaskAssignment taskAssignment = getTaskAssignmentById(id);

        taskAssignment.setTaskName(taskAssignmentDTO.getTaskName());
        taskAssignment.setStartDate(taskAssignmentDTO.getStartDate());
        taskAssignment.setEndDate(taskAssignmentDTO.getEndDate());
        taskAssignment.setDescription(taskAssignmentDTO.getDescription());
        taskAssignment.setUpdatedBy(taskAssignmentDTO.getUpdatedBy());
        taskAssignment.setUpdatedDate(taskAssignmentDTO.getUpdatedDate());

        Technologies technology = technologyRepository.findById(taskAssignmentDTO.getTechnologyId())
                .orElseThrow(() -> new RuntimeException("Technology not found with ID: " + taskAssignmentDTO.getTechnologyId()));
        taskAssignment.setTechnologies(technology);

        List<Users> users = taskAssignmentDTO.getUserIds().stream()
                .map(userId -> userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId)))
                .collect(Collectors.toList());
        taskAssignment.setTaskAssigneeUsers(users);

        return taskAssignmentRepository.save(taskAssignment);
    }

    // Delete Task Assignment
    @Override
    public void deleteTaskAssignment(long id) {
        TaskAssignment taskAssignment = getTaskAssignmentById(id);
        taskAssignmentRepository.delete(taskAssignment);
    }
}
