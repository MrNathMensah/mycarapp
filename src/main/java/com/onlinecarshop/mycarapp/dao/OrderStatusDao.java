package com.onlinecarshop.mycarapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinecarshop.mycarapp.pojo.OrderStatus;

@Repository
public interface OrderStatusDao extends JpaRepository<OrderStatus, Long> {

}
