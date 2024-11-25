package com.project.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task_submission", uniqueConstraints = @UniqueConstraint(columnNames = "task_submission_id"))
public class TaskSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_submission_id")
    private long taskSubmissionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Courses course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "technology_id", nullable = false)
    private Technologies technology;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_assignment_id", nullable = false)
    private TaskAssignment taskAssignment;

    @Column(name = "task_name")
    private String taskName;

    @ManyToMany
    @JoinTable(
            name = "task_submission_users", // Join table name
            joinColumns = @JoinColumn(name = "task_submission_id"), // Foreign key in the join table referencing `Technologies`
            inverseJoinColumns = @JoinColumn(name = "user_id") // Foreign key in the join table referencing `Users`
    )
    private List<Users> taskSubmissionUsers;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status")
    private String Status;

    @Column(name = "description")
    private String description;

    @Column(name = "created_by")
    private long createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by")
    private long updatedBy;

    @Column(name = "updated_date")
    private long updatedDate;
}
