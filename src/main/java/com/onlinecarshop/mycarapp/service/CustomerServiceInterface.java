package com.onlinecarshop.mycarapp.service;

import java.util.List;

import com.onlinecarshop.mycarapp.exception.CustomerNotFoundException;
import com.onlinecarshop.mycarapp.exception.IdNotFoundException;
import com.onlinecarshop.mycarapp.pojo.Customer;

public interface CustomerServiceInterface {
	
	Customer authenticate(Customer customer);
	
	Customer findById(Long id)throws IdNotFoundException;
	
	Customer validateCustomer(Long id);
	
	boolean addCustomer(Customer customer);
	
	List<Customer> getCustomerName(String customerName)throws CustomerNotFoundException;
	
	List<Customer> getCustomerEmail(String customerEmail)throws CustomerNotFoundException;
	
	List<Customer> getCustomerNumber(String customerNumber)throws CustomerNotFoundException;

}
