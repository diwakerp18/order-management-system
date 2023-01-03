package com.organization.ordermanagementsystem.converter;

import com.organization.ordermanagementsystem.dto.OrdersDTO;
import com.organization.ordermanagementsystem.entity.Orders;
import org.springframework.stereotype.Service;

@Service
public class OrdersDtoToEntity {

    public Orders dtoToEntity(OrdersDTO ordersDTO) {

    Orders orders =
        Orders.builder()
            .id(ordersDTO.getId())
            .customerId(ordersDTO.getCustomerId())
            .customerName(ordersDTO.getCustomerName())
            .deliveryAddress(ordersDTO.getDeliveryAddress())
            .orderTotal(ordersDTO.getOrderTotal())
            .deliveryCharge(ordersDTO.getDeliveryCharge())
            .discountAmmount(ordersDTO.getDiscountAmount())
            .productPrice(ordersDTO.getProductPrice())
            .deleted(ordersDTO.getDeleted())
            .build();

        return orders;
    }
}
