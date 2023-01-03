package com.organization.ordermanagementsystem.entity;

import java.math.BigDecimal;
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
@Table(name = "Orders")
public class Orders {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CustomerId")
  private Long customerId;

  @Column(name = "CustomerName")
  private String customerName;

  @Column(name = "DeliveryAddress")
  private String deliveryAddress;

  @Column(name = "OrderDate")
  private Date orderDate;

  @Column(name = "DeliveryDate")
  private Date deliveryDate;

  @Column(name = "DeliveryCharge")
  private BigDecimal deliveryCharge;

  @Column(name = "DiscountAmmount")
  private BigDecimal discountAmmount;

  @Column(name = "OrderTotal")
  private BigDecimal orderTotal;

  @Column(name = "PaymentDate")
  private Date paymentDate;

  @Column(name = "Deleted")
  private Boolean deleted;

  @Column(name = "CreatedAt")
  private Date createdAt;

  @Column(name = "UpdatedAt")
  private Date updatedAt;

  public Orders() {

  }
}
