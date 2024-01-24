# InvoiceManagementApp - back-end

This project was written as my Thesis for the IT Systems Development curriculum in TalTech. With the main aim being, to develop a user-based invoicing web application that helps manage invoices & contacts, as well as tracks business transactions in forms of sales- and purchase invoices.  
The previously mentioned functionality would allow users to create and download invoices in PDF-formats, manage their contact book information and enter their business transactions.

The project is predominantly developed using Java as the primary programming language, and it incorporates key technologies such as **Spring**, **Spring Boot**  **Spring Security**, **JPA** (Java Persistence API), and **Maven** for streamlined development and management. The business logic is divided into multiple layers, using a basic **MVC**-like structural pattern (**entity/repository/service/controller**). 

<img src="https://github.com/kentmarcusp/InvoiceManagementBackEnd/assets/54845506/1a7ad429-d2e0-4df1-8765-5ec749ced770" alt="Folder structure." width="30%">

*Back-end file structure*


Project's functionality is based upon simple **CRUD** controllers, that validate, update and construct input and output information.

User registration and verification is built upon a simple self-developed JWT authentication system, that verifies user input, accessibility and credibility through encoded header data.

### Invoice generation
This functionality is carried out through an external library called iTextPDF, which in this project's case, helps construct a PDF-file based on the input parameters. iTextPDF allows to 
### Example of a generated invoice
<img src="https://github.com/kentmarcusp/InvoiceManagementBackEnd/assets/54845506/3ccedb59-062f-41e8-9aa1-f6fe6b492530" alt="Folder structure." width="50%">

### Database
The temporary database solution, was to simply setup an H2Database (an in-memory database) config inside the Spring Boot Project, to manage datasets. The final database consisted of 8 entities.
### ERD-schema of the database
<img src="https://github.com/kentmarcusp/InvoiceManagementBackEnd/assets/54845506/3ae101f7-73c6-4d98-8d37-7148d8b1e860" alt="Folder structure." width="50%">


