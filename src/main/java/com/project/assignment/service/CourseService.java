package com.project.assignment.service;

import com.project.assignment.entity.Courses;

import java.util.List;

public interface CourseService {

    Courses createCourse(Courses course);

    Courses getCourseById(Long courseId);

    List<Courses> getAllCourses();

    Courses updateCourse(Long courseId, Courses updatedCourse);

    void deleteCourse(Long courseId);
}
