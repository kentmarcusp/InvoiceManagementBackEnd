package com.webapp.InvoiceManagementApp.repository;

import com.webapp.InvoiceManagementApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
