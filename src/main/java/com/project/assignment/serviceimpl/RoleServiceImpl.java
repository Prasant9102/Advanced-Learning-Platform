package com.project.assignment.serviceimpl;


import com.project.assignment.entity.Roles;
import com.project.assignment.repository.RoleRepository;
import com.project.assignment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Roles> findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    public Optional<Roles> findById(Long roleId) {
        return roleRepository.findById(roleId);
    }
}
