package com.project.assignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_name"),
        @UniqueConstraint(columnNames = "user_email")
})
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @JsonIgnore
    @Column(name = "user_passwd")
    private String password;

    @Column(name = "user_email")
    private String email;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private RefreshToken refreshToken;

    @Column(name = "reset_token")
    @JsonIgnore
    private String resetToken;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles roleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Courses course;

    @Column(name = "last_logged_in_time")
    private Timestamp lastLoggedInTime;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Courses getCourse() {
        return course;
    }

    public Long getCourseId() {
        return course != null ? course.getCourseId() : null;
    }

    public void setCourseId(Long courseId) {
        if (this.course == null) {
            this.course = new Courses(); // Initialize client if null
        }
        this.course.setCourseId(courseId);
    }
}
