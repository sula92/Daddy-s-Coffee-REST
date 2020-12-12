package com.sula.coffeeshop.repository.custom;

import com.sula.coffeeshop.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee,String> {
}
