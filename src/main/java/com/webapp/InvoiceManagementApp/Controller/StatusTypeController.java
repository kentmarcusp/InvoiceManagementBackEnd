package com.webapp.InvoiceManagementApp.Controller;


import com.webapp.InvoiceManagementApp.Model.StatusType;
import com.webapp.InvoiceManagementApp.Service.StatusTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bill/statustype")
public class StatusTypeController {

    private final StatusTypeService statusTypeService;

    @Autowired
    public StatusTypeController(StatusTypeService statusTypeService) {
        this.statusTypeService = statusTypeService;
    }

    @GetMapping
    @ResponseBody
    public List<StatusType> getAllStatusTypes() {
        return statusTypeService.getStatusTypes();
    }
}
