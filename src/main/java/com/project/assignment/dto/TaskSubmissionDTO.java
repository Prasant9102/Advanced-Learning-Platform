package com.project.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskSubmissionDTO {

    private long taskAssignmentId;
    private String taskName;
    private long courseId;
    private long technologyId;
    private List<Long> userIds;
    private String status;
    private String description;
    private long createdBy;
    private long updatedBy;
    private Date startDate;
    private Date endDate;
}
