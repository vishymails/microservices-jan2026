package com.bvr.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvr.demo.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
