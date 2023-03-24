package com.onlinecarshop.mycarapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinecarshop.mycarapp.pojo.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

}
