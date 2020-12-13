package com.sula.coffeeshop.repository.custom;

import com.sula.coffeeshop.dto.OrderDTO;
import com.sula.coffeeshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.management.loading.MLetContent;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
    public Order getFirstLastOrderIdByOrderByIdDesc();
    @Query("")
    public List<OrderDTO> searchOrder();
}
