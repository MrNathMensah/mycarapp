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

import com.onlinecarshop.mycarapp.dao.ShipmentMethodDao;
import com.onlinecarshop.mycarapp.pojo.ShippmentMethod;
import com.onlinecarshop.mycarapp.service.ShippmentMethodInterfaceImplementation;

@RestController
public class ShipmentMethodController {
	
	@Autowired
	private ShippmentMethodInterfaceImplementation service;
	
	@Autowired
	private ShipmentMethodDao dao;
	
	//saving shipment method information
	@PostMapping("/saveshipmentmethod")
	public ResponseEntity<?> addShipmentMethod(@RequestBody ShippmentMethod shippmentMethod){
		
		boolean serviceCustomer = service.addShipmentInfo(shippmentMethod);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "this is to add shipment method details");
		if(!serviceCustomer) {
			dao.save(shippmentMethod);
			return ResponseEntity.ok("shipment method details saved successfully");
		}
		
		return new ResponseEntity<String>("Shipment method with this ID already exist", HttpStatus.OK);
	}
	
	//Delete by Id for Shipment Method 
	@DeleteMapping("/deleteshipmentmethod/{id}")
	public ResponseEntity<?> deleteShipmentMethod(@PathVariable Long id) {
		dao.deleteById(id);
		return ResponseEntity.ok("Shipment Method details deleted successfully");
	}
	//Select all or Find all Shipment Method details
	@GetMapping("/allshipmentmethod")
	public List<ShippmentMethod> getAllShippmentMethod(){
		return dao.findAll();
	}
	
	//Find Shipment Method details by id
	@GetMapping("/findshipmentmethod/{id}")
	public ResponseEntity<?> findShipmentMethodById(@PathVariable Long id){
		ShippmentMethod customserve = service.findById(id);
		return ResponseEntity.ok(customserve);
	}
	
	
	//Authenticating Shipment Method
	@PostMapping("/shipmentmethodauth")
	public ResponseEntity<?> auth(@RequestBody ShippmentMethod shippmentMethod){
		
		ShippmentMethod custom = service.authenticate(shippmentMethod);
		
		if(custom != null) {
		
		return new ResponseEntity<ShippmentMethod>(custom, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Invalid user input", HttpStatus.OK);
	}
	
	//Shipment Method Validation in Controller
	@PostMapping("/shipmentvalidation")
	public ResponseEntity<?> newShipmentMethod(@RequestBody ShippmentMethod shippmentMethod) {
		ShippmentMethod customer = service.validateShipmentMethod(shippmentMethod.getId());
			if(customer != null) {
				dao.save(shippmentMethod);
			}
			return new ResponseEntity<String>("Id exist", HttpStatus.OK);
		}
		
}
