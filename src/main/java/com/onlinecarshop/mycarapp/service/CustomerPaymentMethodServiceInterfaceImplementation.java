package com.onlinecarshop.mycarapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.onlinecarshop.mycarapp.dao.CustomerPaymentMethodDao;
import com.onlinecarshop.mycarapp.exception.CustomerNotFoundException;
import com.onlinecarshop.mycarapp.exception.CustomerPaymentMethodNotFoundException;
import com.onlinecarshop.mycarapp.exception.IdNotFoundException;
import com.onlinecarshop.mycarapp.pojo.CustomerPaymentMethod;

@Service
public class CustomerPaymentMethodServiceInterfaceImplementation implements CustomerPaymentMethodInterface {
	
	@Autowired
	private CustomerPaymentMethodDao dao;

	//Service Logic for Customer Payment Method Authentication
	@Override
	public CustomerPaymentMethod authenticate(CustomerPaymentMethod customerPaymentMethod) {
		CustomerPaymentMethod customPayment = new CustomerPaymentMethod();
		customPayment.setPaymentType(customerPaymentMethod.getPaymentType());
		customPayment.setAccountNumber(customerPaymentMethod.getAccountNumber());
		
		Example<CustomerPaymentMethod> customPaymentExamp = Example.of(customPayment);
		Optional<CustomerPaymentMethod> option = dao.findOne(customPaymentExamp);
		
		if(option.isPresent()) {
			return option.get();
		}
		return null;
	}

	//Service Logic for finding Customer Payment Method details by ID and help throw ID not found exception
	@Override
	public CustomerPaymentMethod findById(Long id) throws IdNotFoundException {
		return dao.findById(id)
				.stream()
				.filter(paymentMethod -> paymentMethod.getId()== id)
				.findAny()
				.orElseThrow(()-> new IdNotFoundException("Customer payment method id not found"));
	}
	
	//Service Logic for Customer Payment Method Validation
	@Override
	public CustomerPaymentMethod validateCustomerPaymentMethod(Long id) {
		Optional<CustomerPaymentMethod> optional = dao.findById(id);
		 if(optional.isPresent()) {
			 return optional.get();
	}
		return null;
	}

	//Adding Customer Payment Method using id to authenticate
	@Override
	public boolean addCustomerPaymentMethod(CustomerPaymentMethod customerPaymentMethod) {
		CustomerPaymentMethod add = new CustomerPaymentMethod();
		add.setAccountNumber(customerPaymentMethod.getAccountNumber());
		
		Example<CustomerPaymentMethod> customPaymentExamp = Example.of(add);
		Optional<CustomerPaymentMethod> option = dao.findOne(customPaymentExamp);
		if(option.isPresent()) {
			return true;
		}
		return false;
	}
	
	// Service Logic for finding Customer Payment Method details using account number
	@Override
	public List<CustomerPaymentMethod> getCustomerAccountNumber(String accountNumber)
			throws CustomerPaymentMethodNotFoundException {
		List<CustomerPaymentMethod> customerPayment = dao.findAll()
				.stream()
				.filter(custNumber ->custNumber.getAccountNumber().equals(accountNumber) )
				.collect(Collectors.toList());
		if(customerPayment.isEmpty()) {
			throw new CustomerNotFoundException("Customer account number not available");
		}
		return customerPayment;
	}

	@Override
	public Double getTotalCustomerPayment() {
		
		Double totalPayment = dao.findAll()
                                 .stream()
                                 .map(paid->paid.getAmountPaid())
                                 .collect(Collectors.summingDouble(Double::doubleValue));
		
		return totalPayment;
	}

}
