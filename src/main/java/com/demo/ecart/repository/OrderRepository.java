package com.demo.ecart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ecart.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
