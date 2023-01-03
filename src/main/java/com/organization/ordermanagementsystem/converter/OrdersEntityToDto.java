package com.organization.ordermanagementsystem.converter;

import com.organization.ordermanagementsystem.dto.CustomerDTO;
import com.organization.ordermanagementsystem.dto.OrdersDTO;
import com.organization.ordermanagementsystem.entity.Customer;
import com.organization.ordermanagementsystem.entity.Orders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class OrdersEntityToDto {

    public OrdersDTO entityToDto(Orders orders) {

        if (isNull(orders)) {
            return OrdersDTO.builder().build();
        }

    return OrdersDTO.builder()
        .id(orders.getId())
        .orderDate(orders.getOrderDate())
        .orderTotal(orders.getOrderTotal())
        .paymentDate(orders.getPaymentDate())
        .customerId(orders.getCustomerId())
        .customerName(orders.getCustomerName())
        .deliveryCharge(orders.getDeliveryCharge())
        .updatedAt(orders.getUpdatedAt())
        .deliveryAddress(orders.getDeliveryAddress())
        .deleted(orders.getDeleted())
        .productPrice(orders.getProductPrice())
        .discountAmount(orders.getDiscountAmmount())
        .customerType(orders.getCustomerType())
        .createdAt(orders.getCreatedAt())
        .build();
    }

    public List<OrdersDTO> entityToDto(List<Orders> ordersList) {
        return ordersList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
