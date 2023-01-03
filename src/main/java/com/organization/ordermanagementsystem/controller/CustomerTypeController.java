package com.organization.ordermanagementsystem.controller;

import com.organization.ordermanagementsystem.dto.CustomerTypeDTO;
import com.organization.ordermanagementsystem.exception.OrderManagementServiceException;
import com.organization.ordermanagementsystem.service.CustomerTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("customerType")
public class CustomerTypeController {

    @Autowired
    private CustomerTypeService customerTypeService;

    @PostMapping("/create")
    public CustomerTypeDTO createCustomerType(@Valid @RequestBody CustomerTypeDTO customerTypeDTO) throws  Exception {
        CustomerTypeDTO retVal = customerTypeService.createCustomerType(customerTypeDTO);
        if (isNull(retVal)){
            throw new OrderManagementServiceException("CustomerType Creation failed");
        }

        return retVal;
    }
}
