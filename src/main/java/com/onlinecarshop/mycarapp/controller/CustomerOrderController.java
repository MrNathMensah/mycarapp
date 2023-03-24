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

import com.onlinecarshop.mycarapp.dao.CustomerOrderDao;
import com.onlinecarshop.mycarapp.pojo.CustomerOrder;
import com.onlinecarshop.mycarapp.service.CustomerOrderServiceInterfaceImplementation;



@RestController
public class CustomerOrderController {
	
	@Autowired
	private CustomerOrderServiceInterfaceImplementation service;
	
	@Autowired
	private CustomerOrderDao dao;
	
	//saving customer order information
	@PostMapping("/savecustomerorder")
	public ResponseEntity<?> addCustomerOrder(@RequestBody CustomerOrder customerOrder){
		
		boolean serviceCustomer = service.addCustomerOrder(customerOrder);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "this is to add customer");
		if(!serviceCustomer) {
			dao.save(customerOrder);
			return ResponseEntity.ok("Customer Order saved successfully");
		}
		
		return new ResponseEntity<String>("Customer with this Basic Car Details already exist", HttpStatus.OK);
	}
	
	//Delete by Id Method for customer Order 
	@DeleteMapping("/deletecustomerorder/{id}")
	public ResponseEntity<?> deleteCustomerOrder(@PathVariable Long id) {
		dao.deleteById(id);
		return ResponseEntity.ok("Customer Order deleted successfully");
	}
	
	//Select all or Find all Customer Order details
	@GetMapping("/allcustomerorder")
	public List<CustomerOrder> getAllCustomerOrders(){
		return dao.findAll();
	}
	
	//Find Customer Order by id
	@GetMapping("/findorder/{id}")
	public ResponseEntity<?> findCustomerOrderById(@PathVariable Long id){
		CustomerOrder customOrderServe = service.findById(id);
		return ResponseEntity.ok(customOrderServe);
	}
	
	
	//Authenticating Customer Order Method
	@PostMapping("/customerorderauth")
	public ResponseEntity<?> auth(@RequestBody CustomerOrder customerOrder){
		
		CustomerOrder custom = service.authenticate(customerOrder);
		
		if(custom != null) {
		
		return new ResponseEntity<CustomerOrder>(custom, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Invalid user input", HttpStatus.OK);
	}
	
	//Customer Order Validation Method in Controller
	@PostMapping("/customerordervalidation")
	public ResponseEntity<?> newCustomerOrder(@RequestBody CustomerOrder customerOrder) {
		CustomerOrder customer = service.validateCustomer(customerOrder.getId());
			if(customer != null) {
				dao.save(customerOrder);
			}
			return new ResponseEntity<String>("Id exist", HttpStatus.OK);
		}
		
}
