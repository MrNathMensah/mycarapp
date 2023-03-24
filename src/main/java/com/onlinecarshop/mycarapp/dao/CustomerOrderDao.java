package com.onlinecarshop.mycarapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinecarshop.mycarapp.pojo.CustomerOrder;

public interface CustomerOrderDao extends JpaRepository<CustomerOrder, Long> {

}
