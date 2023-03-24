package com.onlinecarshop.mycarapp.service;

import java.util.List;

import com.onlinecarshop.mycarapp.exception.CustomerOrderNotFoundException;
import com.onlinecarshop.mycarapp.exception.IdNotFoundException;
import com.onlinecarshop.mycarapp.pojo.CustomerOrder;

public interface CustomerOrderInterface {
	
	CustomerOrder authenticate(CustomerOrder customerOrder);
	
	CustomerOrder findById(Long id)throws IdNotFoundException;
	
	CustomerOrder validateCustomer(Long id);
	
	boolean addCustomerOrder(CustomerOrder customerOrder);
	
	List<CustomerOrder> getCustomerOrderAddress(String address)throws CustomerOrderNotFoundException;
	
	List<CustomerOrder> getCustomerOrderCarDetails(String carBasicDetails)throws CustomerOrderNotFoundException;
	

}
