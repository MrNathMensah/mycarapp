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
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinecarshop.mycarapp.dao.CustomerDao;
import com.onlinecarshop.mycarapp.pojo.Customer;
import com.onlinecarshop.mycarapp.service.CustomerServiceImplement;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerServiceImplement service;
	
	@Autowired
	private CustomerDao dao;
	
	//saving customer information
	@PostMapping("/savecustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
		
		boolean serviceCustomer = service.addCustomer(customer);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "this is to add customer");
		if(!serviceCustomer) {
			dao.save(customer);
			return ResponseEntity.ok("Customer is saved successfully");
		}
		
		return new ResponseEntity<String>("Customer with this Email already exist", HttpStatus.OK);
	}
	
	//Delete by Id Method for customer 
	@DeleteMapping("/deletecustomer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
		dao.deleteById(id);
		return ResponseEntity.ok("Customer deleted successfully");
	}
	//Select all or Find all Customers Method
	@GetMapping("/allcustomers")
	public List<Customer> getAllCustomers(){
		return dao.findAll();
	}
	
	//Find Customer by id
	@GetMapping("/findcustomer/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable Long id){
		Customer customserve = service.findById(id);
		return ResponseEntity.ok(customserve);
	}
	
	
	//Updating customer records method
	@PutMapping("/customerupdate/{id}")
	public Customer updateEmployeeProfile(@RequestBody Customer newCustomer, @PathVariable Long id){
		return dao.findById(id)
				.map(customer -> {
				customer.setCustomerName(newCustomer.getCustomerName());
				customer.setCustomeEmail(newCustomer.getCustomeEmail());
				customer.setAddress(newCustomer.getAddress());
				customer.setCustomerNumber(newCustomer.getCustomerNumber());
				customer.setCustomerPassword(newCustomer.getCustomerPassword());
				
				return dao.save(customer);
				})
				.orElseGet(()->{
					newCustomer.setId(id);
					return dao.save(newCustomer);
				});
	}
	
	//Authenticating Customer Method
	@PostMapping("/customerauth")
	public ResponseEntity<?> auth(@RequestBody Customer customer){
		
		Customer custom = service.authenticate(customer);
		
		if(custom != null) {
		
		return new ResponseEntity<Customer>(custom, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Invalid user input", HttpStatus.OK);
	}
	
	//Customer Validation Method in Controller
	@PostMapping("/customervalidation")
	public ResponseEntity<?> newCustomer(@RequestBody Customer custom) {
		Customer customer = service.validateCustomer(custom.getId());
			if(customer != null) {
				dao.save(custom);
			}
			return new ResponseEntity<String>("Id exist", HttpStatus.OK);
		}
		

}
