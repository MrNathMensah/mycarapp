package com.onlinecarshop.mycarapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinecarshop.mycarapp.pojo.CustomerPaymentMethod;

public interface CustomerPaymentMethodDao extends JpaRepository<CustomerPaymentMethod, Long> {

}
