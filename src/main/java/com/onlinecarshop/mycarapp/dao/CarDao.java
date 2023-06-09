package com.onlinecarshop.mycarapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinecarshop.mycarapp.pojo.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Long> {

}
