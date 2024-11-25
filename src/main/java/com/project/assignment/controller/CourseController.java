package com.project.assignment.controller;

import com.project.assignment.entity.Courses;
import com.project.assignment.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alp/api/v1/courses")
public class CourseController {


    @Autowired
    private CourseService courseService;

    // Create a new course
    @PostMapping
    public ResponseEntity<Courses> createCourse(@RequestBody Courses course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    // Get a course by ID
    @GetMapping("/{courseId}")
    public ResponseEntity<Courses> getCourseById(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    // Get all courses
    @GetMapping
    public ResponseEntity<List<Courses>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // Update a course by ID
    @PutMapping("/{courseId}")
    public ResponseEntity<Courses> updateCourse(@PathVariable Long courseId, @RequestBody Courses updatedCourse) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, updatedCourse));
    }

    // Delete a course by ID
    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok("Course deleted successfully with ID: " + courseId);
    }
}
