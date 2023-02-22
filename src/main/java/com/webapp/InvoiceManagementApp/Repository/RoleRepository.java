package com.webapp.InvoiceManagementApp.Repository;

import com.webapp.InvoiceManagementApp.Model.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
