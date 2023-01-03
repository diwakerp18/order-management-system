package com.organization.ordermanagementsystem.service;

import com.organization.ordermanagementsystem.converter.CustomerDtoToEntity;
import com.organization.ordermanagementsystem.converter.CustomerDtoToExistingEntity;
import com.organization.ordermanagementsystem.converter.CustomerEntityToDto;
import com.organization.ordermanagementsystem.dto.CustomerDTO;
import com.organization.ordermanagementsystem.entity.Customer;
import com.organization.ordermanagementsystem.exception.OrderManagementServiceException;
import com.organization.ordermanagementsystem.helper.consts.CustomerConstants;
import com.organization.ordermanagementsystem.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import static java.util.Objects.isNull;

@Service
@Slf4j
public class CustomerService {

  @Autowired
  CustomerRepo customerRepo;
  @Autowired
  CustomerDtoToEntity customerDtoToEntity;
  @Autowired
  CustomerEntityToDto customerEntityToDto;
  @Autowired
  CustomerDtoToExistingEntity customerDtoToExistingEntity;

  public List<CustomerDTO> getCustomers() throws Exception {
    log.info("Fetching All Customer Records");
    List<Customer> customers = customerRepo.findAllByDeletedFalse();
    if (customers.isEmpty()){
      throw new OrderManagementServiceException("Not A Single Record Found");
    }

    List<CustomerDTO> customerDTOS = customerEntityToDto.entityToDto(customers);
    return customerDTOS;
  }

  public CustomerDTO createCustomer(CustomerDTO customerDTO) throws Exception {
    log.info("creating new customer record");
    checkForNullInputs(customerDTO);

    Customer existingCustomer = customerRepo.findByContactNumberAndDeletedFalse(customerDTO.getContactNumber());
    if (!isNull(existingCustomer)) {
      throw new OrderManagementServiceException("An Customer Already Exists for contactNumber : "+ customerDTO.getContactNumber());
    }

    setDefaultValues(customerDTO);
    Customer customer = getEntityToSave(customerDTO);
    customerRepo.save(customer);
    CustomerDTO customerResponse = customerEntityToDto.entityToDto(customer);

    return customerResponse;
  }

  public CustomerDTO getCustomerById(Long id) throws Exception {
    log.info("searching for customer with id :"+ id);
    Customer customer = customerRepo.findByIdAndAndDeletedFalse(id);
    if (isNull(customer)){
      throw new OrderManagementServiceException("No Record Found With id :"+ id);
    }

    CustomerDTO customerDTO = customerEntityToDto.entityToDto(customer);
    return customerDTO;
  }

  public Customer updateCustomerRecord(CustomerDTO customerDTO) throws Exception {
    if (isNull(customerDTO)){
      throw new OrderManagementServiceException("Input Can`t be null");
    }

    log.info("searching for customer with id :"+ customerDTO.getId());
    Customer existingRecord = customerRepo.findByIdAndAndDeletedFalse(customerDTO.getId());
    if (isNull(existingRecord)){
      throw new OrderManagementServiceException("No Record Found For Id :"+ customerDTO.getId());
    }
    customerDtoToExistingEntity.dtoToExistingEntity(existingRecord, customerDTO);

    return customerRepo.save(existingRecord);
  }

  public Customer hardDeleteCustomer(Long id) throws Exception {
    log.info("hard deleting customer with id :"+ id);
    Customer customer = customerRepo.findByIdAndAndDeletedFalse(id);
    if (isNull(customer)){
      throw new OrderManagementServiceException("Customer not exists with id :"+ id);
    }
    customerRepo.delete(customer);
    return customer;
  }

  public Customer softDeleteCustomer(Long id) throws Exception {
    log.info("soft deleting customer with id :"+ id);
    Customer customer = customerRepo.findByIdAndAndDeletedFalse(id);
    if (isNull(customer)){
      throw new OrderManagementServiceException("Customer not exists with id :"+ id);
    }

    customer.setDeleted(true);
    customer.setUpdatedAt(new Date());
    customerRepo.save(customer);

    return customer;
  }

  private Customer getEntityToSave(CustomerDTO customerDTO) {

    Customer customer = customerDtoToEntity.dtoToEntity(customerDTO);
    customer.setCreatedAt(new Date());
    customer.setUpdatedAt(new Date());
    return customer;
  }

  private void setDefaultValues(CustomerDTO customerDTO) {

    if (isNull(customerDTO.getDeleted())){
      customerDTO.setDeleted(CustomerConstants.DEFAULT_DELETED);
    }
    if (isNull(customerDTO.getCustomerType())){
      customerDTO.setCustomerType(CustomerConstants.DEFAULT_CUSTOMER_TYPE);
    }
  }

  private void checkForNullInputs(CustomerDTO customerDTO) {

    if(isNull(customerDTO)){
      throw new OrderManagementServiceException("Input Can`t be empty");
    }
    if(isNull(customerDTO.getName())){
      throw new OrderManagementServiceException("Name Can`t be empty");
    }
    if (isNull(customerDTO.getContactNumber())){
      throw new OrderManagementServiceException("Phone Number Can`t be empty");
    }
    if (isNull(customerDTO.getEmail())){
      throw new OrderManagementServiceException("Email Id Can`t be empty");
    }

  }

}

