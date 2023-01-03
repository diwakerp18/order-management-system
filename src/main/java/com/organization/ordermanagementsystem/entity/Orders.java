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

  @Column(name = "ProductPrice")
  private Integer productPrice;

  @Column(name = "CustomerName")
  private String customerName;

  @Column(name = "DeliveryAddress")
  private String deliveryAddress;

  @Column(name = "OrderDate")
  private Date orderDate;

  @Column(name = "DeliveryCharge")
  private Integer deliveryCharge;

  @Column(name = "DiscountAmmount")
  private Integer discountAmmount;

  @Column(name = "OrderTotal")
  private Integer orderTotal;

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
