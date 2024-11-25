package com.project.assignment.controller;

import com.project.assignment.dto.TechnologyDTO;
import com.project.assignment.entity.Technologies;
import com.project.assignment.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alp/api/v1/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    // Create Technology
    @PostMapping
    public ResponseEntity<Technologies> createTechnology(@RequestBody TechnologyDTO technologyDTO) {
        Technologies createdTechnology = technologyService.createTechnology(technologyDTO);
        return new ResponseEntity<>(createdTechnology, HttpStatus.CREATED);
    }

    // Get All Technologies
    @GetMapping
    public ResponseEntity<List<Technologies>> getAllTechnologies() {
        List<Technologies> technologies = technologyService.getAllTechnologies();
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }

    // Get Technology by ID
    @GetMapping("/{technologyId}")
    public ResponseEntity<Technologies> getTechnologyById(@PathVariable("technologyId") long technologyId) {
        try {
            Technologies technology = technologyService.getTechnologyById(technologyId);
            return new ResponseEntity<>(technology, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if technology not found
        }
    }

    // Update Technology
    @PutMapping("/{technologyId}")
    public ResponseEntity<Technologies> updateTechnology(@PathVariable("technologyId") long technologyId,
                                                         @RequestBody TechnologyDTO technologyDTO) {
        try {
            Technologies updatedTechnology = technologyService.updateTechnology(technologyId, technologyDTO);
            return new ResponseEntity<>(updatedTechnology, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if technology not found
        }
    }

    // Delete Technology
    @DeleteMapping("/{technologyId}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable("technologyId") long technologyId) {
        try {
            technologyService.deleteTechnology(technologyId);
            return new ResponseEntity<>(HttpStatus.OK);  // Return 200 for successful delete
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if technology not found
        }
    }

}
