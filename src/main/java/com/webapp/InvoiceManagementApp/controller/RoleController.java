package com.webapp.InvoiceManagementApp.controller;

import com.webapp.InvoiceManagementApp.model.Role;
import com.webapp.InvoiceManagementApp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    @ResponseBody
    //@CrossOrigin
    public List<Role> getAllRoles() {
        return roleService.getRoles();
    }


}
