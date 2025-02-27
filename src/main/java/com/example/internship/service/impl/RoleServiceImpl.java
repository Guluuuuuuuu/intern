package com.example.internship.service.impl;

import com.example.internship.entity.Role;
import com.example.internship.service.RoleService;
import com.example.internship.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> list() {
        return roleRepository.findAll();
    }
} 