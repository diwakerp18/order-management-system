package com.organization.ordermanagementsystem.controller;

import com.organization.ordermanagementsystem.dto.OrdersDTO;
import com.organization.ordermanagementsystem.exception.OrderManagementServiceException;
import com.organization.ordermanagementsystem.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @PostMapping("/create")
    public OrdersDTO createOrder(@Valid @RequestBody OrdersDTO ordersDTO) throws  Exception {
        OrdersDTO retVal = ordersService.createOrder(ordersDTO);
        if (isNull(retVal)){
            throw new OrderManagementServiceException("Order Creation failed");
        }
        return retVal;
    }

}
