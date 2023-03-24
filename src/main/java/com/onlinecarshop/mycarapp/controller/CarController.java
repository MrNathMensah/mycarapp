package com.onlinecarshop.mycarapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinecarshop.mycarapp.dao.CarDao;
import com.onlinecarshop.mycarapp.pojo.Car;
import com.onlinecarshop.mycarapp.service.CarServiceInterfaceImplementation;


@RestController
public class CarController {
	
	@Autowired
	private CarServiceInterfaceImplementation service;
	
	@Autowired
	private CarDao dao;
	
	//saving car information
	@PostMapping("/savecar")
	public ResponseEntity<?> addCar(@RequestBody Car car){
		
		boolean serviceCar = service.addCar(car);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "this is to add car");
		if(!serviceCar) {
			dao.save(car);
			return ResponseEntity.ok("Car is saved successfully");
		}
		
		return new ResponseEntity<String>("Car with this Basic car details already exist", HttpStatus.OK);
	}
	
	//Delete by Id Method for car 
	@DeleteMapping("/deletecar/{id}")
	public ResponseEntity<?> deleteCar(@PathVariable Long id) {
		dao.deleteById(id);
		return ResponseEntity.ok("Car details deleted successfully");
	}
	
	//Select all or Find all Car Method
	@GetMapping("/allcars")
	public List<Car> getAllCars(){
		return dao.findAll();
	}
	
	//Find Car by id
	@GetMapping("/findcar/{id}")
	public ResponseEntity<?> findCarById(@PathVariable Long id){
		Car carServe = service.findById(id);
		return ResponseEntity.ok(carServe);
	}
	
	  //Authenticating Car Method
	@PostMapping("/carauth")
	public ResponseEntity<?> auth(@RequestBody Car car){
			
		Car lorry = service.authenticate(car);
			
		if(lorry != null) {
			
		return new ResponseEntity<Car>(lorry, HttpStatus.OK);
		}
			
		return new ResponseEntity<String>("Invalid user input", HttpStatus.OK);
		}
		
		//Car Validation Method in Controller
		@PostMapping("/carvalidation")
		public ResponseEntity<?> newCar(@RequestBody Car car) {
			Car lorry = service.validateCar(car.getId());
				if(lorry != null) {
					dao.save(car);
				}
				return new ResponseEntity<String>("Id exist", HttpStatus.OK);
			}
}
