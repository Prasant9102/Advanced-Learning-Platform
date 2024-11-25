package com.project.assignment.repository;

import com.project.assignment.entity.Courses;
import com.project.assignment.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {
}
