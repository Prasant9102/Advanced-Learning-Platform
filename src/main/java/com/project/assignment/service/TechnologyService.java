package com.project.assignment.service;

import com.project.assignment.dto.TechnologyDTO;
import com.project.assignment.entity.Technologies;

import java.util.List;

public interface TechnologyService {


    public Technologies createTechnology(TechnologyDTO technologyDTO);

    public List<Technologies> getAllTechnologies();

    public Technologies getTechnologyById(long technologyId);

    public Technologies updateTechnology(long technologyId, TechnologyDTO technologyDTO);

    public void deleteTechnology(long technologyId);
}
