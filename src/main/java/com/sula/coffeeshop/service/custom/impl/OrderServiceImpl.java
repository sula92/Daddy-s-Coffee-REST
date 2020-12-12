package com.sula.coffeeshop.service.custom.impl;

import com.sula.coffeeshop.dto.ItemDTO;
import com.sula.coffeeshop.dto.OrderDTO;
import com.sula.coffeeshop.dto.OrderDetailDTO;
import com.sula.coffeeshop.entity.Item;
import com.sula.coffeeshop.entity.Order;
import com.sula.coffeeshop.repository.custom.OrderRepository;
import com.sula.coffeeshop.service.custom.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public String getNewOrderId() throws Exception {
        String lastItemCode = orderRepository.getFirstLastOrderIdByOrderByIdDesc().getId();
        if (lastItemCode == null) {
            return "OD001";
        } else {
            int maxId = Integer.parseInt(lastItemCode.replace("OD", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "OD00" + maxId;
            } else if (maxId < 100) {
                id = "OD0" + maxId;
            } else {
                id = "OD" + maxId;
            }

            return id;
        }
    }

    @Override
    public List<OrderDTO> searchOrder() throws Exception {
        return null;
    }

    @Override
    public void placeOrder(OrderDTO order, List<OrderDetailDTO> orderDetails) throws Exception {

    }

    @Override
    public OrderDTO getOrder(String id) {
        return null;
    }

    @Override
    public List<OrderDTO> getAllOrders() {

        List<Order> allOrders = orderRepository.findAll();
        List<OrderDTO> orders = new ArrayList<>();
        for (Order order : allOrders) {
            List<OrderDetailDTO> orderDetailDTOS=new ArrayList<>();
            order.getOrderDetailList().stream().forEach(orderDetail -> {
                orderDetailDTOS.add(new OrderDetailDTO(orderDetail.getItem().getCode(), orderDetail.getQty(), orderDetail.getUnitPrice() ));
            });
            orders.add(new OrderDTO(order.getId(), order.getDate().toString(), order.getCustomer().getId(),orderDetailDTOS));
        }
        return orders;
    }
}
