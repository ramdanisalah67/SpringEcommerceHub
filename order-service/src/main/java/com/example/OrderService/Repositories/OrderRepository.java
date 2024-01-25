package com.example.OrderService.Repositories;

import com.example.OrderService.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
