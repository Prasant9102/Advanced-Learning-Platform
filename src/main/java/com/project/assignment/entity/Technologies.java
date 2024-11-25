package com.project.assignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "technologies", uniqueConstraints = @UniqueConstraint(columnNames = "technology_name"))
public class Technologies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technology_id")
    private long technologyId;

    @Column(name = "technology_name", nullable = false)
    private String technologyName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Courses course;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "mentor", nullable = false)
    private String mentor;

    @ManyToMany
    @JoinTable(
            name = "technology_users", // Join table name
            joinColumns = @JoinColumn(name = "technology_id"), // Foreign key in the join table referencing `Technologies`
            inverseJoinColumns = @JoinColumn(name = "user_id") // Foreign key in the join table referencing `Users`
    )
    private List<Users> technologyUsers;

    @Column(name = "description")
    private String description;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDate updatedDate;
}
