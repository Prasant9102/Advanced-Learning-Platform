package com.project.assignment.serviceimpl;

import com.project.assignment.dto.TaskSubmissionDTO;
import com.project.assignment.entity.*;
import com.project.assignment.repository.*;
import com.project.assignment.service.TaskSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskSubmissionServiceImpl implements TaskSubmissionService {

    @Autowired
    private TaskSubmissionRepository taskSubmissionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private TaskAssignmentRepository taskAssignmentRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public TaskSubmission createTaskSubmission(TaskSubmissionDTO taskSubmissionDTO) {
        TaskSubmission taskSubmission = new TaskSubmission();

        // Validate and set course
        Courses course = courseRepository.findById(taskSubmissionDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        taskSubmission.setCourse(course);

        // Validate and set technology
        Technologies technology = technologyRepository.findById(taskSubmissionDTO.getTechnologyId())
                .orElseThrow(() -> new RuntimeException("Technology not found"));
        taskSubmission.setTechnology(technology);

        // Validate and set task assignment
        TaskAssignment taskAssignment = taskAssignmentRepository.findById(taskSubmissionDTO.getTaskAssignmentId())
                .orElseThrow(() -> new RuntimeException("Task Assignment not found"));
        taskSubmission.setTaskAssignment(taskAssignment);

        // Validate and associate userIds
        if (taskSubmissionDTO.getUserIds() != null && !taskSubmissionDTO.getUserIds().isEmpty()) {
            List<Users> users = taskSubmissionDTO.getUserIds().stream()
                    .map(userId -> userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId)))
                    .collect(Collectors.toList());
            taskSubmission.setTaskSubmissionUsers(users);
        } else {
            throw new RuntimeException("User IDs cannot be null or empty");
        }

        // Set additional fields
        taskSubmission.setTaskName(taskSubmissionDTO.getTaskName());
        taskSubmission.setStartDate(taskSubmissionDTO.getStartDate());
        taskSubmission.setEndDate(taskSubmissionDTO.getEndDate());
        taskSubmission.setStatus(taskSubmissionDTO.getStatus());
        taskSubmission.setDescription(taskSubmissionDTO.getDescription());
        taskSubmission.setCreatedBy(taskSubmissionDTO.getCreatedBy());
        taskSubmission.setCreatedDate(new Date(System.currentTimeMillis()));

        return taskSubmissionRepository.save(taskSubmission);
    }


    @Override
    public List<TaskSubmission> getAllTaskSubmissions() {
        return taskSubmissionRepository.findAll();
    }

    @Override
    public TaskSubmission getTaskSubmissionById(long id) {
        return taskSubmissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task Submission not found"));
    }


    @Override
    public TaskSubmission updateTaskSubmission(long id, TaskSubmissionDTO taskSubmissionDTO) {
        // Fetch the existing TaskSubmission by ID
        TaskSubmission taskSubmission = getTaskSubmissionById(id);

        // Update Course
        Courses course = courseRepository.findById(taskSubmissionDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        taskSubmission.setCourse(course);

        // Update Technology
        Technologies technology = technologyRepository.findById(taskSubmissionDTO.getTechnologyId())
                .orElseThrow(() -> new RuntimeException("Technology not found"));
        taskSubmission.setTechnology(technology);

        // Update TaskAssignment
        TaskAssignment taskAssignment = taskAssignmentRepository.findById(taskSubmissionDTO.getTaskAssignmentId())
                .orElseThrow(() -> new RuntimeException("Task Assignment not found"));
        taskSubmission.setTaskAssignment(taskAssignment);

        // Update status and description
        taskSubmission.setStatus(taskSubmissionDTO.getStatus());
        taskSubmission.setDescription(taskSubmissionDTO.getDescription());

        // Update the list of users (if userIds are provided in the payload)
        if (taskSubmissionDTO.getUserIds() != null && !taskSubmissionDTO.getUserIds().isEmpty()) {
            List<Users> users = taskSubmissionDTO.getUserIds().stream()
                    .map(userId -> userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId)))
                    .collect(Collectors.toList());
            taskSubmission.setTaskSubmissionUsers(users);
        } else {
            // If no userIds are provided, you could either clear the existing users
            // or leave them as is depending on business logic.
            // For now, let's assume you want to remove all users if no userIds are provided.
            taskSubmission.setTaskSubmissionUsers(new ArrayList<>());
        }

        // Update updateBy and updateDate fields
        taskSubmission.setUpdatedBy(taskSubmissionDTO.getUpdatedBy());
        taskSubmission.setUpdatedDate(new Date(System.currentTimeMillis()).getTime());

        // Save and return the updated TaskSubmission
        return taskSubmissionRepository.save(taskSubmission);
    }


    @Override
    public void deleteTaskSubmission(long id) {
        TaskSubmission taskSubmission = getTaskSubmissionById(id);
        taskSubmissionRepository.delete(taskSubmission);
    }
}
