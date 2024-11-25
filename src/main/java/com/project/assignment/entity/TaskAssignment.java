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
@Table(name = "task_assignment", uniqueConstraints = @UniqueConstraint(columnNames = "task_assignment_id"))
public class TaskAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_assignment_id")
    private long taskAssignmentId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "task_assignee_users", // Join table name
            joinColumns = @JoinColumn(name = "task_assignment_id"), // Foreign key in the join table referencing `Technologies`
            inverseJoinColumns = @JoinColumn(name = "user_id") // Foreign key in the join table referencing `Users`
    )
    private List<Users> taskAssigneeUsers;

    @Column(name = "created_by")
    private long createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by")
    private long updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "technology_id", nullable = false)
    private Technologies technologies;
}
