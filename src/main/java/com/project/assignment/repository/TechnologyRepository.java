package com.project.assignment.repository;

import com.project.assignment.entity.Technologies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technologies, Long> {
}
