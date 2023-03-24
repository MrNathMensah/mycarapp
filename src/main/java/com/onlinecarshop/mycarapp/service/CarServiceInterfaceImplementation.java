package com.onlinecarshop.mycarapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.onlinecarshop.mycarapp.dao.CarDao;
import com.onlinecarshop.mycarapp.exception.IdNotFoundException;
import com.onlinecarshop.mycarapp.pojo.Car;

@Service
public class CarServiceInterfaceImplementation implements CarServiceInterface {
	
	@Autowired
	private CarDao dao;
	
	//Service Logic for Car Authentication
	@Override
	public Car authenticate(Car car) {
		Car lorry = new Car();
		lorry.setCarBasicDetails(lorry.getCarBasicDetails());
		
		Example<Car> carExamp = Example.of(lorry);
		Optional<Car> option = dao.findOne(carExamp);
		
		if(option.isPresent()) {
			return option.get();
		}
		return null;
	}

	@Override
	public Car findById(Long id) throws IdNotFoundException {
		return dao.findById(id)
				.stream()
				.filter(car -> car.getId()== id)
				.findAny()
				.orElseThrow(()-> new IdNotFoundException("Car id not found"));
	}
	
	//Service Logic for Car Validation
	@Override
	public Car validateCar(Long id) {
		Optional<Car> optional = dao.findById(id);
		 if(optional.isPresent()) {
			 return optional.get();
			 }
		return null;
	}

	//Adding Car using Car Basic Details to authenticate
	@Override
	public boolean addCar(Car car) {
		Car add = new Car();
		add.setCarBasicDetails(car.getCarBasicDetails());
		
		Example<Car> carExamp = Example.of(add);
		Optional<Car> option = dao.findOne(carExamp);
		if(option.isPresent()) {
			return true;
		}
		return false;
	}

}
