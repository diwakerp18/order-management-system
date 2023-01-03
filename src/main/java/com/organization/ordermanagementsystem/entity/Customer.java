package com.organization.ordermanagementsystem.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;

@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "Customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "Name")
  private String name;

  @Column(name = "Email")
  private String email;

  @Column(name = "ContactNumber")
  private String contactNumber;

  @Column(name = "CustomerType")
  private String customerType;

  @Column(name = "Deleted")
  private Boolean deleted;

  @Column(name = "CreatedAt")
  private Date createdAt;

  @Column(name = "UpdatedAt")
  private Date updatedAt;

  public Customer() {

  }
}
