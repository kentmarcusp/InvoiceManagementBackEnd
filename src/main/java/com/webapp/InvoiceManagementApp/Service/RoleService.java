package com.webapp.InvoiceManagementApp.Service;

import com.webapp.InvoiceManagementApp.Model.Role;
import com.webapp.InvoiceManagementApp.Repository.RoleRepository;
import jakarta.transaction.Transactional;
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
