package com.organization.ordermanagementsystem.repository;

import com.organization.ordermanagementsystem.entity.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerTypeRepo extends JpaRepository<CustomerType, Long> {

    CustomerType findByIdAndAndDeletedFalse(Long id);
    CustomerType findByTypeAndDeletedFalse(String type);

}
