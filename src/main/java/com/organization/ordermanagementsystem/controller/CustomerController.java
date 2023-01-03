package com.organization.ordermanagementsystem.controller;

import static java.util.Objects.isNull;

import com.organization.ordermanagementsystem.dto.CustomerDTO;
import com.organization.ordermanagementsystem.entity.Customer;
import com.organization.ordermanagementsystem.exception.OrderManagementServiceException;
import com.organization.ordermanagementsystem.service.CustomerService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  // get all customer
  @GetMapping("/getAll")
  public List<CustomerDTO> getAllCustomer() throws Exception {
    List<CustomerDTO> customers = customerService.getCustomers();
    return customers;
  }

  // create Customer
  @PostMapping("/create")
  public CustomerDTO createCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws  Exception {
    CustomerDTO retVal = customerService.createCustomer(customerDTO);
    if (isNull(retVal)){
      throw new OrderManagementServiceException("Customer Creation failed");
    }

    return retVal;
  }

  // get customer by id
  @GetMapping("/get/{id}")
  public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) throws Exception {
    if (isNull(id) || id <= 0){
      throw new OrderManagementServiceException("Customer Id should be a positive value");
    }
    CustomerDTO customerDTO = customerService.getCustomerById(id);
    return ResponseEntity.ok(customerDTO);
  }

  // update customer record
  @PostMapping("/update/{id}")
  public Customer updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) throws  Exception {
    if (isNull(id) || id <= 0){
      throw new OrderManagementServiceException("Customer Id should be a positive value");
    }
    customerDTO.setId(id);

    Customer retVal = customerService.updateCustomerRecord(customerDTO);
    if (isNull(retVal)){
      throw new OrderManagementServiceException("Faild to update customer record");
    }
    return retVal;
  }

  // Hard delete customer
  @DeleteMapping("/hardDelete/{id}")
  public ResponseEntity<OrderManagementServiceException> hardDeleteCustomer(@PathVariable Long id) throws Exception {
    customerService.hardDeleteCustomer(id);
    return ResponseEntity.ok(new OrderManagementServiceException("Sucessfully deleted customer with id :" + id));
  }

  // Soft delete customer
  @PostMapping("/softDelete/{id}")
  public ResponseEntity<OrderManagementServiceException> softDeleteCustomer(@PathVariable Long id) throws Exception {
    customerService.softDeleteCustomer(id);
    return ResponseEntity.ok(new OrderManagementServiceException("Sucessfully deleted customer with id :" + id));
  }

}

