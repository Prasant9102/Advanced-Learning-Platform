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
public class TaskAssignmentDTO {

    private String taskName;
    private Date startDate;
    private Date endDate;
    private String description;
    private long technologyId;
    private List<Long> userIds;
    private long createdBy;
    private Date createdDate;
    private long updatedBy;
    private Date updatedDate;
}
