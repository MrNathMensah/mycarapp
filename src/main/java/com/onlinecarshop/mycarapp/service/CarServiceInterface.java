package com.onlinecarshop.mycarapp.service;

import com.onlinecarshop.mycarapp.exception.IdNotFoundException;
import com.onlinecarshop.mycarapp.pojo.Car;


public interface CarServiceInterface {
	
    Car authenticate(Car car);
	
	Car findById(Long id)throws IdNotFoundException;
	
	Car validateCar(Long id);
	
	boolean addCar(Car car);

}
