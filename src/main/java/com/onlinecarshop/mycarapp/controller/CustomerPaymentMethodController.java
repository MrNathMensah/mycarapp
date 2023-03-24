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

import com.onlinecarshop.mycarapp.dao.CustomerPaymentMethodDao;
import com.onlinecarshop.mycarapp.pojo.CustomerPaymentMethod;
import com.onlinecarshop.mycarapp.service.CustomerPaymentMethodServiceInterfaceImplementation;



@RestController
public class CustomerPaymentMethodController {
	
	@Autowired
	private CustomerPaymentMethodServiceInterfaceImplementation service;
	
	@Autowired
	private CustomerPaymentMethodDao dao;
	
	//saving customer payment information
	@PostMapping("/savepaymentinfo")
	public ResponseEntity<?> addCustomerPaymentMethod(@RequestBody CustomerPaymentMethod customerPaymentMethod){
		
		boolean serviceCustomer = service.addCustomerPaymentMethod(customerPaymentMethod);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "this is to add customer payment information");
		if(!serviceCustomer) {
			dao.save(customerPaymentMethod);
			return ResponseEntity.ok("Customer payment details saved successfully");
		}
		
		return new ResponseEntity<String>("Customer Payment with this ID already exist", HttpStatus.OK);
	}
	
	//Delete by Id for payment details 
	@DeleteMapping("/deletepayment/{id}")
	public ResponseEntity<?> deleteShipmentMethod(@PathVariable Long id) {
		dao.deleteById(id);
		return ResponseEntity.ok("Shipping details deleted successfully");
	}
	//Select all or Find all Shipping 
	@GetMapping("/allpaymentdetails")
	public List<CustomerPaymentMethod> getAllCustomers(){
		return dao.findAll();
	}
	
	//Find Payment detail by id
	@GetMapping("/findpayment/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable Long id){
		CustomerPaymentMethod customserve = service.findById(id);
		return ResponseEntity.ok(customserve);
	}
	
	
	//Authenticating Payment Method
	@PostMapping("/paymentauth")
	public ResponseEntity<?> auth(@RequestBody CustomerPaymentMethod customerPaymentMethod){
		
		CustomerPaymentMethod custom = service.authenticate(customerPaymentMethod);
		
		if(custom != null) {
		
		return new ResponseEntity<CustomerPaymentMethod>(custom, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Invalid user input", HttpStatus.OK);
	}
	
	//Payment Validation Method in Controller
	@PostMapping("/paymentvalidation")
	public ResponseEntity<?> newPaymentMethod(@RequestBody CustomerPaymentMethod customerPaymentMethod) {
		CustomerPaymentMethod customer = service.validateCustomerPaymentMethod(customerPaymentMethod.getId());
			if(customer != null) {
				dao.save(customerPaymentMethod);
			}
			return new ResponseEntity<String>("Id exist", HttpStatus.OK);
		}
	
	@GetMapping("/getTotalPayment")
	public ResponseEntity<?> getTotalPayment(){
		Double totalAmount = service.getTotalCustomerPayment();
		return ResponseEntity.status(HttpStatus.OK).body(totalAmount);
	}
		
}
