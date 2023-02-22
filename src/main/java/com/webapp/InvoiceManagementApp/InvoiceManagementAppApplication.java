package com.webapp.InvoiceManagementApp;

import com.webapp.InvoiceManagementApp.Model.BillType;
import com.webapp.InvoiceManagementApp.Model.Customer;
import com.webapp.InvoiceManagementApp.Model.Role;
import com.webapp.InvoiceManagementApp.Model.StatusType;
import com.webapp.InvoiceManagementApp.Repository.BillTypeRepository;
import com.webapp.InvoiceManagementApp.Repository.CustomerRepository;
import com.webapp.InvoiceManagementApp.Repository.RoleRepository;
import com.webapp.InvoiceManagementApp.Repository.StatusTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.management.relation.RoleUnresolved;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class InvoiceManagementAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceManagementAppApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository
            , RoleRepository roleRepository
            , BillTypeRepository billTypeRepository
            , StatusTypeRepository statusTypeRepository) {
        return args -> {
            Role roleUser = new Role(1, "ROLE_USER", new Date());
            Role roleAdmin = new Role(2, "ROLE_ADMIN", new Date());
            roleRepository.saveAll(List.of(roleUser, roleAdmin));

            BillType billTypeBuy = new BillType(1, "Ostuarve", "test");
            BillType billTypeSell = new BillType(2, "Müügiarve", "test");

            billTypeRepository.saveAll(List.of(billTypeBuy, billTypeSell));


            StatusType statusTypeSuccess = new StatusType(1, "Invoice has been paid");
            StatusType statusTypeAwait = new StatusType(2, "Awaiting for confirmation");
            statusTypeRepository.saveAll(List.of(statusTypeSuccess, statusTypeAwait));

            Customer testUser = new Customer(-1, "TestUser", "TestPassword", "test@gmail.com"
                    , new Date(), new Date(), "EE123456", "SWED", roleUser);
            Customer testUser2 = new Customer(-1, "TestUser2", "TestPassword2", "test2@gmail.com"
                    , new Date(), new Date(), null, null, roleAdmin);

            customerRepository.saveAll(List.of(testUser, testUser2));
        };
    }

}
