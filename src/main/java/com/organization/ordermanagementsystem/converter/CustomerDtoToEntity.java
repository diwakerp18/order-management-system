package com.organization.ordermanagementsystem.converter;

import com.organization.ordermanagementsystem.dto.CustomerDTO;
import com.organization.ordermanagementsystem.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerDtoToEntity {

  public Customer dtoToEntity(CustomerDTO customerDTO) {

    Customer customer =
        Customer.builder()
            .id(customerDTO.getId())
            .customerType(customerDTO.getCustomerType())
            .name(customerDTO.getName())
            .email(customerDTO.getEmail())
            .contactNumber(customerDTO.getContactNumber())
            .deleted(customerDTO.getDeleted())
            .build();

    return customer;
  }
}
