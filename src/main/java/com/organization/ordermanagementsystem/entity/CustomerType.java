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
@Table(name = "CustomerType")
public class CustomerType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "Type")
  private String type;

  @Column(name = "Deleted")
  private Boolean deleted;

  @Column(name = "CreatedAt")
  private Date createdAt;

  @Column(name = "UpdatedAt")
  private Date updatedAt;

  @Column(name = "UpdatedBy")
  private Long updatedBy;

  @Column(name = "CreatedBy")
  private Long createdBy;

  public CustomerType() {

  }
}
