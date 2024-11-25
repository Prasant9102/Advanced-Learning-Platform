package com.project.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = {"role_name", "course_id"}))
public class Roles {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;

    @Column(name = "status")
    private String status;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "created_by")
    private Integer createdBy;


    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_by")
    private Integer updatedBy;


    @Column(name = "updated_date")
    private Timestamp updatedDate;
}
