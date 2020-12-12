package com.sula.coffeeshop.repository.custom;

import com.sula.coffeeshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
