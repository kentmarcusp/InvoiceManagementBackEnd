package com.webapp.InvoiceManagementApp.Controller;

import com.webapp.InvoiceManagementApp.Model.BillType;
import com.webapp.InvoiceManagementApp.Model.Role;
import com.webapp.InvoiceManagementApp.Service.BillTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bill/billtype")
public class BillTypeController {

    private final BillTypeService billTypeService;

    @Autowired
    public BillTypeController(BillTypeService billTypeService){
        this.billTypeService = billTypeService;

    }
    @GetMapping
    @ResponseBody
    public List<BillType> getAllRoles() {
        return billTypeService.getBillTypes();
    }
}
