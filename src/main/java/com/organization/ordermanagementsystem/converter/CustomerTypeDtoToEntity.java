package com.organization.ordermanagementsystem.converter;

import com.organization.ordermanagementsystem.dto.CustomerTypeDTO;
import com.organization.ordermanagementsystem.entity.CustomerType;
import org.springframework.stereotype.Service;

@Service
public class CustomerTypeDtoToEntity {

    public CustomerType dtoToEntity(CustomerTypeDTO customerTypeDTO) {

        CustomerType customerType =
                CustomerType.builder()
                        .id(customerTypeDTO.getId())
                        .type(customerTypeDTO.getType())
                        .deleted(customerTypeDTO.getDeleted())
                        .build();

        return customerType;
    }
}
