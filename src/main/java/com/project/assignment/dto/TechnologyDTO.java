package com.project.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDTO {

    private String technologyName;
    private long courseId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String mentor;
    private String description;
    private String createdBy;
    private LocalDate createdDate;
    private List<Long> technologyUsers;  // List of user IDs

}
