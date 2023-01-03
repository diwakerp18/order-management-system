package com.organization.ordermanagementsystem.service;

import com.organization.ordermanagementsystem.converter.CustomerDtoToEntity;
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

    Customer existingCustomer = getCustomerForContact(customerDTO.getContactNumber());
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

  public Customer updateStudentRecord(StudentRecordDto studentRecordDto) throws Exception {
    log.info("searching for student with id :"+ studentRecordDto.getId());
    checkForEmptyInputs(studentRecordDto);

    StudentRecord existingStudentRecord = studentRecordRepo.findByIdAndAndDeletedFalse(studentRecordDto.getId());
    if (isNull(existingStudentRecord)){
      throw new OrganizationServiceException("No Record Found For Id :"+ studentRecordDto.getId());
    }

    setDefaultValues(studentRecordDto);
    studentRecordDtoToExistingEntityConverter.dtoToExistingEntity(existingStudentRecord, studentRecordDto);
    return studentRecordRepo.save(existingStudentRecord);
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

  public Customer getCustomerForContact(String contactNumber) throws Exception {

    if (isNull(contactNumber)){
      throw new OrderManagementServiceException("Contact Number Can`t Be Empty");
    }

    log.info("Searching for customer details with contactNumber : " + contactNumber);
    Customer customer = customerRepo.findByContactNumberAndDeletedFalse(contactNumber);

    return customer;
  }

  public List<StudentRecordDto> getStudentsWithFilters(String branch, String batch, String role) throws Exception {
    getVerified(branch, batch, role);
    log.info("Fetching All Students Records With Branch {}, Batch {}, And Role {} ", branch, batch, role);
    List<StudentRecord> studentRecords = studentRecordRepo.findByBranchAndBatchAndAndRoleAndDeletedFalse(branch, batch, role);
    if (studentRecords.isEmpty()){
      throw new OrganizationServiceException("Not A Single Record Found");
    }

    List<StudentRecordDto> studentRecordDtos = studentRecordEntityToDtoConverter.entityToDto(studentRecords);
    return studentRecordDtos;
  }

  private void getVerified(String branch, String batch, String role) throws Exception {
    if (isNull(branch)){
      throw new OrganizationServiceException("branch can`t` be empty");
    }
    branchService.verifyBranch(branch);

    if (isNull(batch)){
      throw new OrganizationServiceException("batch can`t` be empty");
    }
    batchService.verifyBatch(batch);

    if (isNull(role)){
      throw new OrganizationServiceException("role can`t` be empty");
    }
    roleService.verifyRole(role);
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

  private void checkForEmptyInputs(StudentRecordDto studentRecordDto) {

    if(studentRecordDto.getStudentName().isEmpty()){
      throw new OrganizationServiceException("Student Name Can`t be empty");
    }
    if (studentRecordDto.getCollegeName().isEmpty()){
      throw new OrganizationServiceException("College Name Can`t be empty");
    }
    if (studentRecordDto.getEmailId().isEmpty()){
      throw new OrganizationServiceException("Email Id Can`t be empty");
    }
    if (isNull(studentRecordDto.getPhoneNumber())){
      throw new OrganizationServiceException("Phone Number Can`t be empty");
    }
    if (studentRecordDto.getBatch().isEmpty()){
      throw new OrganizationServiceException("Batch Can`t be empty");
    }
    if (studentRecordDto.getBranch().isEmpty()){
      throw new OrganizationServiceException("Branch Can`t be empty");
    }
    if (isNull(studentRecordDto.getRollNumber())){
      throw new OrganizationServiceException("Roll Number Can`t be empty");
    }
  }

}

