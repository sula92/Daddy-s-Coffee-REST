package com.sula.coffeeshop.repository.custom;

import com.sula.coffeeshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.loading.MLetContent;

public interface OrderRepository extends JpaRepository<Order,String> {
    public Order getFirstLastOrderIdByOrderByIdDesc();
}
