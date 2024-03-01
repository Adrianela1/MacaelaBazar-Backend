package com.macaela.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macaela.api.models.order.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
