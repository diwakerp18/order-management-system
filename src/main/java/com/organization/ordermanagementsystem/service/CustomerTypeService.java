package com.organization.ordermanagementsystem.service;

import com.organization.ordermanagementsystem.converter.CustomerTypeDtoToEntity;
import com.organization.ordermanagementsystem.converter.CustomerTypeEntityToDto;
import com.organization.ordermanagementsystem.dto.CustomerDTO;
import com.organization.ordermanagementsystem.dto.CustomerTypeDTO;
import com.organization.ordermanagementsystem.entity.Customer;
import com.organization.ordermanagementsystem.entity.CustomerType;
import com.organization.ordermanagementsystem.exception.OrderManagementServiceException;
import com.organization.ordermanagementsystem.helper.consts.CustomerTypeConstants;
import com.organization.ordermanagementsystem.repository.CustomerTypeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class CustomerTypeService {

    @Autowired
    CustomerTypeRepo customerTypeRepo;
    @Autowired
    CustomerTypeDtoToEntity customerTypeDtoToEntity;
    @Autowired
    CustomerTypeEntityToDto customerTypeEntityToDto;

    public CustomerTypeDTO createCustomerType(CustomerTypeDTO customerTypeDTO) throws Exception {
        if (customerTypeDTO.getType().isEmpty()){
            throw new OrderManagementServiceException("Type can`t be Empty");
        }

        CustomerType existingCustomerType = customerTypeRepo.findByTypeAndDeletedFalse(customerTypeDTO.getType());
        if (!isNull(existingCustomerType)) {
            throw new OrderManagementServiceException("An CustomerType Already Exists for Type : "+ customerTypeDTO.getType());
        }

        log.info("creating new customerType");
        CustomerType customerType = getEntityToSave(customerTypeDTO);
        customerTypeRepo.save(customerType);
        CustomerTypeDTO response = customerTypeEntityToDto.entityToDto(customerType);

        return response;
    }

    private CustomerType getEntityToSave(CustomerTypeDTO customerTypeDTO) {

        CustomerType customerType = customerTypeDtoToEntity.dtoToEntity(customerTypeDTO);
        customerType.setCreatedAt(new Date());
        customerType.setUpdatedAt(new Date());
        customerType.setDeleted(CustomerTypeConstants.DEFAULT_DELETED);

        return customerType;
    }

    public CustomerTypeDTO getCustomerTypeById(Long id) throws Exception {
        log.info("searching for customer type with id :"+ id);
        CustomerType customerType = customerTypeRepo.findByIdAndAndDeletedFalse(id);
        if (isNull(customerType)){
            throw new OrderManagementServiceException("No Customer Type Found With id :"+ id);
        }

        CustomerTypeDTO customerTypeDTO = customerTypeEntityToDto.entityToDto(customerType);
        return customerTypeDTO;
    }


}
