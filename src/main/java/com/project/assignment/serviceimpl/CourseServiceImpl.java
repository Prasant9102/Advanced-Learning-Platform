package com.project.assignment.serviceimpl;

import com.project.assignment.entity.Courses;
import com.project.assignment.repository.CourseRepository;
import com.project.assignment.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Courses createCourse(Courses course) {
        return courseRepository.save(course);
    }

    @Override
    public Courses getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
    }

    @Override
    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Courses updateCourse(Long courseId, Courses updatedCourse) {
        Courses existingCourse = getCourseById(courseId);

        existingCourse.setCourseName(updatedCourse.getCourseName());
        existingCourse.setStartDate(updatedCourse.getStartDate());
        existingCourse.setEndDate(updatedCourse.getEndDate());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setUpdatedBy(updatedCourse.getUpdatedBy());
        existingCourse.setUpdatedDate(updatedCourse.getUpdatedDate());

        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
