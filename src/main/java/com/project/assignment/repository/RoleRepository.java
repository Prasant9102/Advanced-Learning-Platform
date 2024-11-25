package com.project.assignment.repository;

import com.project.assignment.entity.Roles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Roles, Long> {

    List<Roles> findByRoleName(String roleName);
}
