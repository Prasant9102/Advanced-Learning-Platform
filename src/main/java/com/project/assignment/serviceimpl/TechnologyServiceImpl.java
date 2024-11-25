package com.project.assignment.serviceimpl;

import com.project.assignment.dto.TechnologyDTO;
import com.project.assignment.entity.Courses;
import com.project.assignment.entity.Technologies;
import com.project.assignment.entity.Users;
import com.project.assignment.repository.CourseRepository;
import com.project.assignment.repository.TechnologyRepository;
import com.project.assignment.repository.UserRepository;
import com.project.assignment.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Technology
    public Technologies createTechnology(TechnologyDTO technologyDTO) {
        // Fetch Course by ID
        Courses course = courseRepository.findById(technologyDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Fetch Users by their IDs
        List<Users> users = userRepository.findAllById(technologyDTO.getTechnologyUsers());

        // Create and save Technologies entity
        Technologies technology = Technologies.builder()
                .technologyName(technologyDTO.getTechnologyName())
                .course(course)
                .startDate(technologyDTO.getStartDate())
                .endDate(technologyDTO.getEndDate())
                .mentor(technologyDTO.getMentor())
                .description(technologyDTO.getDescription())
                .createdBy(technologyDTO.getCreatedBy())
                .createdDate(technologyDTO.getCreatedDate())
                .technologyUsers(users)
                .build();

        return technologyRepository.save(technology);
    }

    // Read (Get All Technologies)
    public List<Technologies> getAllTechnologies() {
        return technologyRepository.findAll();
    }

    // Read (Get Technology by ID)
    public Technologies getTechnologyById(long technologyId) {
        return technologyRepository.findById(technologyId)
                .orElseThrow(() -> new RuntimeException("Technology not found"));
    }

    // Update Technology
    public Technologies updateTechnology(long technologyId, TechnologyDTO technologyDTO) {
        Technologies existingTechnology = technologyRepository.findById(technologyId)
                .orElseThrow(() -> new RuntimeException("Technology not found"));

        // Update the fields
        existingTechnology.setTechnologyName(technologyDTO.getTechnologyName());
        existingTechnology.setCourse(courseRepository.findById(technologyDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found")));
        existingTechnology.setStartDate(technologyDTO.getStartDate());
        existingTechnology.setEndDate(technologyDTO.getEndDate());
        existingTechnology.setMentor(technologyDTO.getMentor());
        existingTechnology.setDescription(technologyDTO.getDescription());
        existingTechnology.setUpdatedBy(technologyDTO.getCreatedBy());  // Assuming createdBy field will be used for update
        existingTechnology.setUpdatedDate(technologyDTO.getCreatedDate());

        // Update Users
        List<Users> users = userRepository.findAllById(technologyDTO.getTechnologyUsers());
        existingTechnology.setTechnologyUsers(users);

        return technologyRepository.save(existingTechnology);
    }

    // Delete Technology
    public void deleteTechnology(long technologyId) {
        Technologies technology = technologyRepository.findById(technologyId)
                .orElseThrow(() -> new RuntimeException("Technology not found"));

        technologyRepository.delete(technology);
    }

}
