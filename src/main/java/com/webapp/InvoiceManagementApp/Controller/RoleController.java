package com.webapp.InvoiceManagementApp.Controller;

import com.webapp.InvoiceManagementApp.Model.Customer;
import com.webapp.InvoiceManagementApp.Model.Role;
import com.webapp.InvoiceManagementApp.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @ResponseBody
    public List<Role> getAllRoles() {
        return roleService.getRoles();
    }


}
