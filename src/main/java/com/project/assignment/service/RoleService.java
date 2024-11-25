package com.project.assignment.service;

import com.project.assignment.entity.Roles;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    public List<Roles> findByRoleName(String roleName);

    public Optional<Roles> findById(Long roleId);
}
