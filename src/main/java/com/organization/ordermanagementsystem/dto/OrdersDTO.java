package com.organization.ordermanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdersDTO {

    private Long id;
    private Long customerId;
    private String customerName;
    private String deliveryAddress;
    private Integer deliveryCharge;
    private Integer discountAmount;
    private Date orderDate;
    private Integer orderTotal;
    private Integer productPrice;
    private String customerType;
    private Date paymentDate;
    private Date createdAt;
    private Date updatedAt;
    private Boolean deleted;
}
