package com.organization.ordermanagementsystem.converter;

import com.organization.ordermanagementsystem.dto.CustomerTypeDTO;
import com.organization.ordermanagementsystem.entity.CustomerType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class CustomerTypeEntityToDto {

    public CustomerTypeDTO entityToDto(CustomerType customerType) {

        if (isNull(customerType)) {
            return CustomerTypeDTO.builder().build();
        }

        return CustomerTypeDTO.builder()
            .id(customerType.getId())
            .type(customerType.getType())
            .deleted(customerType.getDeleted())
            .updatedAt(customerType.getUpdatedAt())
            .createdAt(customerType.getCreatedAt())
            .build();
    }

    public List<CustomerTypeDTO> entityToDto(List<CustomerType> customerTypeList) {
        return customerTypeList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
