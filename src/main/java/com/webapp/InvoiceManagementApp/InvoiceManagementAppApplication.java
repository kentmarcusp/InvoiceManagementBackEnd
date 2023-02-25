package com.webapp.InvoiceManagementApp;

import com.webapp.InvoiceManagementApp.Model.*;
import com.webapp.InvoiceManagementApp.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.webapp.InvoiceManagementApp")
public class InvoiceManagementAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceManagementAppApplication.class, args);
    }

    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
                                        RoleRepository roleRepository,
                                        InvoiceTypeRepository invoiceTypeRepository,
                                        StatusTypeRepository statusTypeRepository,
                                        ContactTypeRepository contactTypeRepository,
                                        CompanyContactInfoRepository companyContactInfoRepository,
                                        InvoiceRepository invoiceRepository,
                                        InvoiceRowRepository invoiceRowRepository) {
        return args -> {

            /* Set "role" properties */
            Role roleUser = new Role();
            roleUser.setRole_id(1);
            roleUser.setCreatedAt(new Date());
            roleUser.setName("testName");
            Role roleAdmin = new Role();
            roleAdmin.setRole_id(2);
            roleAdmin.setCreatedAt(new Date());
            roleAdmin.setName("testName");
            roleRepository.save(roleUser);
            roleRepository.save(roleAdmin);

            /* Set "invoiceType" properties */
            InvoiceType invoiceTypeBuy = new InvoiceType();
            invoiceTypeBuy.setInvoiceType_id(1);
            invoiceTypeBuy.setName("Ostuarve");
            invoiceTypeBuy.setDescription("Ostuarve test kirjeldus");

            InvoiceType invoiceTypeSell = new InvoiceType();
            invoiceTypeSell.setInvoiceType_id(2);
            invoiceTypeSell.setName("Müügiarve");
            invoiceTypeSell.setDescription("Müügiarve test kirjeldus");

            invoiceTypeRepository.save(invoiceTypeBuy);
            invoiceTypeRepository.save(invoiceTypeSell);

            /* Set "contactType" properties */
            ContactType contactTypeOwner = new ContactType();
            contactTypeOwner.setContactType_id(1);
            contactTypeOwner.setStatus("Owner");

            ContactType contactTypeCustomer = new ContactType();
            contactTypeCustomer.setContactType_id(2);
            contactTypeCustomer.setStatus("Customer");

            contactTypeRepository.save(contactTypeOwner);
            contactTypeRepository.save(contactTypeCustomer);

            /* Set "statusType" properties */
            StatusType statusTypeSuccess = new StatusType();
            statusTypeSuccess.setStatusType_id(1);
            statusTypeSuccess.setStatus("Payment completed");

            StatusType statusTypeAwait = new StatusType();
            statusTypeAwait.setStatusType_id(2);
            statusTypeAwait.setStatus("Awaiting payment");

            statusTypeRepository.save(statusTypeSuccess);
            statusTypeRepository.save(statusTypeAwait);

            /* Set "customer" properties*/
            Customer testUser = new Customer();
            testUser.setPassword("password");
            testUser.setEmail("test@email.com");
            testUser.setCreated_at(new Date());
            testUser.setUpdated_at(new Date());
            testUser.setIban("EE123123123123");
            testUser.setBankName("Swedbank");
            testUser.setRole(roleUser);

            customerRepository.save(testUser);

            /* Set "CompanyContactInfo" properties*/
            CompanyContactInfo testCompany = new CompanyContactInfo();
            testCompany.setContactName("Fups");
            testCompany.setEmail("Fups@test.ee");
            testCompany.setAddress("Õie 2-1");
            testCompany.setRegisterCode("EE11612");
            testCompany.setPhoneNumber("590645223");
            testCompany.setVatNumber("EE1112121");
            testCompany.setContactType(contactTypeCustomer);
            testCompany.setCustomer(testUser);

            companyContactInfoRepository.save(testCompany);

            /* Set "Invoice" properties*/

            Invoice tempInvoiceInfo = new Invoice();
            tempInvoiceInfo.setInvoiceNumber(1);
            tempInvoiceInfo.setInvoicePriceSum(10.00);
            tempInvoiceInfo.setVatValue(20.00);
            tempInvoiceInfo.setSurchargePercentage(15.00);
            tempInvoiceInfo.setCreated_at(new Date());
            tempInvoiceInfo.setDue_date(new Date());
            tempInvoiceInfo.setIssuerCompanyName("fupsi kompanii");
            tempInvoiceInfo.setDescription("fupsi kompanii description");
            tempInvoiceInfo.setCustomer(testUser);
            tempInvoiceInfo.setCompanyContactInfo(testCompany);
            tempInvoiceInfo.setStatusType(statusTypeSuccess);
            tempInvoiceInfo.setInvoiceType(invoiceTypeBuy);

            invoiceRepository.save(tempInvoiceInfo);


            /* Set "InvoiceRow" properties */

            InvoiceRow invoiceRowFields = new InvoiceRow();
            invoiceRowFields.setRowNumber(1);
            invoiceRowFields.setVatPercentage(20.00);
            invoiceRowFields.setTotalSum(100.00);
            invoiceRowFields.setVatSum(25.00);
            invoiceRowFields.setTotalSumWithoutVat(150.00);
            invoiceRowFields.setDescription("Description jne....");
            invoiceRowFields.setInvoice(tempInvoiceInfo);

            invoiceRowRepository.save(invoiceRowFields);


/*
            InvoiceType invoiceTypeBuy = new InvoiceType(1, "Ostuarve", "test");
            InvoiceType invoiceTypeSell = new InvoiceType(2, "Müügiarve", "test");
            invoiceTypeRepository.saveAll(List.of(invoiceTypeBuy, invoiceTypeSell));

            ContactType contactTypeOwner = new ContactType(1, "Company owner");
            ContactType contactTypeCustomer = new ContactType(2, "Customer");
            contactTypeRepository.saveAll(List.of(contactTypeCustomer, contactTypeOwner));

            StatusType statusTypeSuccess = new StatusType(1, "Invoice has been paid");
            StatusType statusTypeAwait = new StatusType(2, "Awaiting for confirmation");
            statusTypeRepository.saveAll(List.of(statusTypeSuccess, statusTypeAwait));

            Customer testUser = new Customer(1, "TestUser", "TestPassword", "test@gmail.com"
                    , new Date(), new Date(), "EE123456", "SWED", roleUser);
            Customer testUser2 = new Customer(2, "TestUser2", "TestPassword2", "test2@gmail.com"
                    , new Date(), new Date(), null, null, roleAdmin);
            customerRepository.saveAll(List.of(testUser, testUser2));

            CompanyContactInfo testCompany = new CompanyContactInfo(1, "TestCompany", "email@email.com", "Sõpruse 111",
                    "11612", "59065248", "EE1234", contactTypeOwner, testUser);
            CompanyContactInfo twilio = new CompanyContactInfo(2, "Twilio", "twilio@email.com", "Sõpruse 1112",
                    "11612", "59065248", "EE1234", contactTypeCustomer, testUser2);
            companyContactInfoRepository.saveAll(List.of(testCompany, twilio));

            Invoice tempInvoiceInfo = new Invoice(1, 1, 50.00, 20.00,
                    0.2, new Date(), new Date(), "Twilio", "null", testUser, twilio, statusTypeSuccess, invoiceTypeBuy);
            invoiceRepository.saveAll(List.of(tempInvoiceInfo));

            InvoiceRow invoiceRowFields = new InvoiceRow(-1, 1,
                    20.00, 50.00, 100.00, 60.00, "test invoice", tempInvoiceInfo);

            invoiceRowRepository.saveAll(List.of(invoiceRowFields));*/

        };
    }

}
