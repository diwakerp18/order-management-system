package com.organization.ordermanagementsystem.service;


import com.organization.ordermanagementsystem.converter.OrdersDtoToEntity;
import com.organization.ordermanagementsystem.converter.OrdersEntityToDto;
import com.organization.ordermanagementsystem.dto.CustomerDTO;
import com.organization.ordermanagementsystem.dto.OrdersDTO;
import com.organization.ordermanagementsystem.entity.Orders;
import com.organization.ordermanagementsystem.exception.OrderManagementServiceException;
import com.organization.ordermanagementsystem.helper.consts.OrdersConstants;
import com.organization.ordermanagementsystem.repository.OrdersRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class OrdersService {

    @Autowired
    OrdersRepo ordersRepo;
    @Autowired
    CustomerService customerService;
    @Autowired
    OrdersDtoToEntity ordersDtoToEntity;
    @Autowired
    OrdersEntityToDto ordersEntityToDto;

    public OrdersDTO createOrder(OrdersDTO ordersDTO) throws Exception {
        log.info("creating new order");

        checkForNullInputs(ordersDTO);
        ordersDTO.setDeliveryCharge(OrdersConstants.DELIVERY_CHARGE);
        ordersDTO.setDeleted(OrdersConstants.DEFAULT_DELETED);
        ordersDTO.setCustomerName(customerService.getCustomerById(ordersDTO.getCustomerId()).getName());

        List<Orders> ordersList = ordersRepo.findByCustomerIdAndDeletedFalse(ordersDTO.getCustomerId());
        Integer countOfOrders = ordersList.size();
        CustomerDTO customerDTO = new CustomerDTO();
        Integer effectiveAmount = ordersDTO.getDeliveryCharge() + ordersDTO.getProductPrice();
        if (!ordersList.isEmpty() && countOfOrders > 9){
            if (countOfOrders > 9 && countOfOrders < 20){
                if (countOfOrders == 10){
                    customerDTO.setCustomerType(OrdersConstants.GOLD_CUSTOMER_TYPE);
                    customerDTO.setId(ordersDTO.getCustomerId());
                    customerService.updateCustomerRecord(customerDTO);
                }
                Integer discountAmount = (int)(effectiveAmount*(10.0f/100.0f));
                ordersDTO.setDiscountAmount(discountAmount);
                Integer orderTotal = effectiveAmount - discountAmount;
                ordersDTO.setOrderTotal(orderTotal);
            } else if (countOfOrders > 19){
                if (countOfOrders == 20){
                    customerDTO.setCustomerType(OrdersConstants.PLATINUM_CUSTOMER_TYPE);
                    customerDTO.setId(ordersDTO.getCustomerId());
                    customerService.updateCustomerRecord(customerDTO);
                }
                Integer discountAmount = (int)(effectiveAmount*(20.0f/100.0f));
                ordersDTO.setDiscountAmount(discountAmount);
                Integer orderTotal = effectiveAmount - discountAmount;
                ordersDTO.setOrderTotal(orderTotal);
            }
        } else {
            ordersDTO.setDiscountAmount(OrdersConstants.DEFAULT_DISCOUNT_AMOUNT);
            ordersDTO.setOrderTotal(effectiveAmount - ordersDTO.getDiscountAmount());
        }

        Orders orders = getEntityToSave(ordersDTO);
        ordersRepo.save(orders);
        OrdersDTO response = ordersEntityToDto.entityToDto(orders);

        return response;
    }

    private void checkForNullInputs(OrdersDTO ordersDTO) {

        if(isNull(ordersDTO)){
            throw new OrderManagementServiceException("Input Can`t be empty");
        }
        if(isNull(ordersDTO.getCustomerId())){
            throw new OrderManagementServiceException("CustomerId Can`t be Null");
        }
        if (isNull(ordersDTO.getDeliveryAddress())){
            throw new OrderManagementServiceException("Delivery Address Can`t be empty");
        }
        if (isNull(ordersDTO.getProductPrice())){
            throw new OrderManagementServiceException("Product Price Can`t be Null");
        }

    }

    private Orders getEntityToSave(OrdersDTO ordersDTO) {

        Orders orders = ordersDtoToEntity.dtoToEntity(ordersDTO);
        orders.setPaymentDate(new Date());
        orders.setOrderDate(new Date());
        orders.setCreatedAt(new Date());
        orders.setUpdatedAt(new Date());
        return orders;
    }

}
