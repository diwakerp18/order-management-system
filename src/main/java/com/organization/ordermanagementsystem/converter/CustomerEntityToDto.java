package com.organization.ordermanagementsystem.converter;

import com.organization.ordermanagementsystem.dto.CustomerDTO;
import com.organization.ordermanagementsystem.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class CustomerEntityToDto {

    public CustomerDTO entityToDto(Customer customer) {

      if (isNull(customer)) {
        return CustomerDTO.builder().build();
      }

      return CustomerDTO.builder()
          .id(customer.getId())
          .customerType(customer.getCustomerType())
          .name(customer.getName())
          .email(customer.getEmail())
          .contactNumber(customer.getContactNumber())
          .deleted(customer.getDeleted())
          .updatedAt(customer.getUpdatedAt())
          .createdAt(customer.getCreatedAt())
          .build();
    }

    public List<CustomerDTO> entityToDto(List<Customer> customerList) {
      return customerList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

  }
