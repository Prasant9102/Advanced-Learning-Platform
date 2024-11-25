package com.project.assignment.service;

import com.project.assignment.dto.TaskSubmissionDTO;
import com.project.assignment.entity.TaskSubmission;

import java.util.List;

public interface TaskSubmissionService {

    TaskSubmission createTaskSubmission(TaskSubmissionDTO taskSubmissionDTO);

    List<TaskSubmission> getAllTaskSubmissions();

    TaskSubmission getTaskSubmissionById(long id);

    TaskSubmission updateTaskSubmission(long id, TaskSubmissionDTO taskSubmissionDTO);

    void deleteTaskSubmission(long id);
}
