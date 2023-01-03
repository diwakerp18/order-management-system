package com.organization.ordermanagementsystem.converter;

import com.organization.ordermanagementsystem.dto.CustomerDTO;
import com.organization.ordermanagementsystem.entity.Customer;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class CustomerDtoToExistingEntity {

    public Customer dtoToExistingEntity(Customer customer, CustomerDTO customerDTO) {
        if (customerDTO.getCustomerType() == null){
            customerDTO.setCustomerType(customer.getCustomerType());
        }
        if (customerDTO.getContactNumber() == null){
            customerDTO.setContactNumber(customer.getContactNumber());
        }
        if (customerDTO.getEmail() == null){
            customerDTO.setEmail(customer.getEmail());
        }
        if (customerDTO.getName() == null){
            customerDTO.setName(customer.getName());
        }
        customer.setCustomerType(customerDTO.getCustomerType());
        customer.setContactNumber(customerDTO.getContactNumber());
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setUpdatedAt(new Timestamp(new Date().getTime()));

        return customer;
    }
}
