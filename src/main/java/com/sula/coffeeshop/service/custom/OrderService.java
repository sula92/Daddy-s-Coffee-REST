package com.sula.coffeeshop.service.custom;

import com.sula.coffeeshop.dto.OrderDTO;
import com.sula.coffeeshop.dto.OrderDetailDTO;

import java.util.List;

public interface OrderService {

    public String getNewOrderId() throws Exception;
    public List<OrderDTO> searchOrder() throws Exception;
    public void placeOrder(OrderDTO order, List<OrderDetailDTO> orderDetails) throws Exception;
    OrderDTO getOrder(String id);
    List<OrderDTO> getAllOrders();

}