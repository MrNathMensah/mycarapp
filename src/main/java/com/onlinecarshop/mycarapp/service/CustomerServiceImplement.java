package com.onlinecarshop.mycarapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import com.onlinecarshop.mycarapp.dao.CustomerDao;
import com.onlinecarshop.mycarapp.exception.CustomerNotFoundException;
import com.onlinecarshop.mycarapp.exception.IdNotFoundException;
import com.onlinecarshop.mycarapp.pojo.Customer;

@Service
public class CustomerServiceImplement implements CustomerServiceInterface {
	
	@Autowired
	private CustomerDao dao;
	
	//Service Logic for Customer Authentication
	@Override
	public Customer authenticate(Customer customer) {
		Customer customa = new Customer();
		customa.setCustomeEmail(customer.getCustomeEmail());
		customa.setCustomerPassword(customer.getCustomerPassword());
		
		Example<Customer> customaExamp = Example.of(customa);
		Optional<Customer> option = dao.findOne(customaExamp);
		
		if(option.isPresent()) {
			return option.get();
		}
		return null;
	}


	//Service Logic for Customer Validation
	@Override
	public Customer validateCustomer(Long id) {
		Optional<Customer> optional = dao.findById(id);
			 if(optional.isPresent()) {
				 return optional.get();
		}
			return null;
	}
	
	//Adding Customer using Email to authenticate
	@Override
	public boolean addCustomer(Customer customer) {
		Customer add = new Customer();
		add.setCustomeEmail(customer.getCustomeEmail());
		
		Example<Customer> customerExamp = Example.of(add);
		Optional<Customer> option = dao.findOne(customerExamp);
		if(option.isPresent()) {
			return true;
		}
		return false;
	}
	// Service Logic for finding Customer details using customer name
	@Override
	public List<Customer> getCustomerName(String customerName) {
		List<Customer> custom = dao.findAll()
				.stream()
				.filter(customer -> customer.getCustomerName().equals(customerName))
				.collect(Collectors.toList());
		if(custom.isEmpty()) {
			throw new CustomerNotFoundException("Customer name not available");
		}
		return custom;
	}
	// Service Logic for finding Customer details using customer email
	@Override
	public List<Customer> getCustomerEmail(String customerEmail) {
		List<Customer> mail = dao.findAll()
				.stream()
				.filter(email -> email.getCustomeEmail().equals(customerEmail))
				.collect(Collectors.toList());
		if(mail.isEmpty()) {
			throw new CustomerNotFoundException("Customer email not available");
		}
		return mail;
	}

	// Service Logic for finding Customer details using customer number
	@Override
	public List<Customer> getCustomerNumber(String customerNumber) {
		List<Customer> customNumber = dao.findAll()
				.stream()
				.filter(custNumber ->custNumber.getCustomerNumber().equals(customerNumber) )
				.collect(Collectors.toList());
		if(customNumber.isEmpty()) {
			throw new CustomerNotFoundException("Customer number not available");
		}
		return customNumber;
	}
	
	//Service Logic for finding Customer by ID and help throw ID not found exception
	@Override
	public Customer findById(Long id) throws IdNotFoundException {
		return dao.findById(id)
				.stream()
				.filter(customer -> customer.getId()== id)
				.findAny()
				.orElseThrow(()-> new IdNotFoundException("Customer id not found"));
	}
}
