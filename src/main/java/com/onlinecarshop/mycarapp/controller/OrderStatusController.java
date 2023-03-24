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

import com.onlinecarshop.mycarapp.dao.OrderStatusDao;
import com.onlinecarshop.mycarapp.pojo.OrderStatus;
import com.onlinecarshop.mycarapp.service.OrderStatusServiceInterfaceImplementation;


@RestController
public class OrderStatusController {
	
	@Autowired
	private OrderStatusServiceInterfaceImplementation service;
	
	@Autowired
	private OrderStatusDao dao;
	
	//saving order status information
	@PostMapping("/saveorderstatus")
	public ResponseEntity<?> addOrderStatusDetail(@RequestBody OrderStatus orderStatus){
		
		boolean serviceOrder = service.addOrderStatusDetails(orderStatus);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "this is to order status");
		if(!serviceOrder) {
			dao.save(orderStatus);
			return ResponseEntity.ok("Order Status Details saved successfully");
		}
		
		return new ResponseEntity<String>("Customer with this ID already exist", HttpStatus.OK);
	}
	
	//Delete by Id Method for order status 
	@DeleteMapping("/deleteorderstatus/{id}")
	public ResponseEntity<?> deleteOrderStatus(@PathVariable Long id) {
		dao.deleteById(id);
		return ResponseEntity.ok("Order Status deleted successfully");
	}
	
	//Select all or Find all Order Status details Method
	@GetMapping("/allorderstatus")
	public List<OrderStatus> getAllOrderStatus(){
		return dao.findAll();
	}
	
	//Find Order Status by id
	@GetMapping("/findorderstatus/{id}")
	public ResponseEntity<?> findOrderStatusById(@PathVariable Long id){
		OrderStatus orderServe = service.findById(id);
		return ResponseEntity.ok(orderServe);
	}
	
	
	//Authenticating Order Status Method
	@PostMapping("/orderstatusauth")
	public ResponseEntity<?> auth(@RequestBody OrderStatus orderStatus){
		
		OrderStatus order = service.authenticate(orderStatus);
		
		if(order != null) {
		
		return new ResponseEntity<OrderStatus>(order, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Invalid user input", HttpStatus.OK);
	}
	
	//Order Status Validation Method in Controller
	@PostMapping("/orderstatusvalidation")
	public ResponseEntity<?> newCustomer(@RequestBody OrderStatus orderStatus) {
		OrderStatus order = service.validateOrderStatus(orderStatus.getId());
			if(order != null) {
				dao.save(orderStatus);
			}
			return new ResponseEntity<String>("Id exist", HttpStatus.OK);
		}
		
}
