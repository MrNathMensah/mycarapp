package com.onlinecarshop.mycarapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.onlinecarshop.mycarapp.dao.CustomerOrderDao;
import com.onlinecarshop.mycarapp.exception.CustomerNotFoundException;
import com.onlinecarshop.mycarapp.exception.CustomerOrderNotFoundException;
import com.onlinecarshop.mycarapp.exception.IdNotFoundException;
import com.onlinecarshop.mycarapp.pojo.CustomerOrder;

@Service
public class CustomerOrderServiceInterfaceImplementation implements CustomerOrderInterface {
	
	@Autowired
	private CustomerOrderDao dao;
	
	//Service Logic for CustomerOrder Authentication(Using address and car basic details)
	@Override
	public CustomerOrder authenticate(CustomerOrder customerOrder) {
		CustomerOrder customOrder = new CustomerOrder();
		customOrder.setAddress(customerOrder.getAddress());
		customOrder.setCarBasicDetails(customerOrder.getCarBasicDetails());
		
		Example<CustomerOrder> customExamp = Example.of(customOrder);
		Optional<CustomerOrder> option = dao.findOne(customExamp);
		
		if(option.isPresent()) {
			return option.get();
		}
		return null;
	}

	@Override
	public CustomerOrder findById(Long id) throws IdNotFoundException {
		return dao.findById(id)
				.stream()
				.filter(customerOrder -> customerOrder.getId()== id)
				.findAny()
				.orElseThrow(()-> new IdNotFoundException("Customer order id not found"));
		
	}
	
	//Service Logic for CustomerOrder Validation
	@Override
	public CustomerOrder validateCustomer(Long id) {
		Optional<CustomerOrder> optional = dao.findById(id);
		 if(optional.isPresent()) {
			 return optional.get();
	}
		return null;
	}
	
	//Adding CustomerOrder using Car Basic Details to authenticate
	@Override
	public boolean addCustomerOrder(CustomerOrder customerOrder) {
		CustomerOrder add = new CustomerOrder();
		add.setCarBasicDetails(customerOrder.getCarBasicDetails());
		
		Example<CustomerOrder> customOrderExamp = Example.of(add);
		Optional<CustomerOrder> option = dao.findOne(customOrderExamp);
		if(option.isPresent()) {
			return true;
		}
		return false;
		
	}

	@Override
	public List<CustomerOrder> getCustomerOrderAddress(String address) throws CustomerOrderNotFoundException {
		List<CustomerOrder> customOrder = dao.findAll()
				.stream()
				.filter(custOrder ->custOrder.getAddress().equals(address) )
				.collect(Collectors.toList());
		if(customOrder.isEmpty()) {
			throw new CustomerNotFoundException("Customer address not available");
		}
		return customOrder;
	}

	@Override
	public List<CustomerOrder> getCustomerOrderCarDetails(String carBasicDetails) throws CustomerOrderNotFoundException {
		List<CustomerOrder> customOrder = dao.findAll()
				.stream()
				.filter(custOrder ->custOrder.getCarBasicDetails().equals(carBasicDetails) )
				.collect(Collectors.toList());
		if(customOrder.isEmpty()) {
			throw new CustomerNotFoundException("Car details not available in order");
		}
		return customOrder;
	}

}
