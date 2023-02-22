package com.webapp.InvoiceManagementApp.Service;

import com.webapp.InvoiceManagementApp.Model.BillType;
import com.webapp.InvoiceManagementApp.Repository.BillTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillTypeService {

    private final BillTypeRepository billTypeRepository;

    @Autowired
    public BillTypeService(BillTypeRepository billTypeRepository) {
        this.billTypeRepository = billTypeRepository;
    }

    public List<BillType> getBillTypes() {
        return billTypeRepository.findAll();
    }
}
