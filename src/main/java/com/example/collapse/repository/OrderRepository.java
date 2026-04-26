package com.example.collapse.repository;

import com.example.collapse.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, String> {
    List<Order> findAllByOrderByCreatedAtDesc();
}
