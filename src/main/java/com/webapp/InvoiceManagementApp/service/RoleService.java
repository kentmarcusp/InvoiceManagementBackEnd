package com.webapp.InvoiceManagementApp.service;

import com.webapp.InvoiceManagementApp.model.Role;
import com.webapp.InvoiceManagementApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;


    @Autowired
    public RoleService(RoleRepository RoleRepository) {
        this.roleRepository = RoleRepository;
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> findRoleById(long id) {
        return roleRepository.findById(id);

    }


}
