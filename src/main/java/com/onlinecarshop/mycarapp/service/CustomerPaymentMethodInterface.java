package com.onlinecarshop.mycarapp.service;

import java.util.List;


import com.onlinecarshop.mycarapp.exception.CustomerPaymentMethodNotFoundException;
import com.onlinecarshop.mycarapp.exception.IdNotFoundException;
import com.onlinecarshop.mycarapp.pojo.CustomerPaymentMethod;

public interface CustomerPaymentMethodInterface {
	
    CustomerPaymentMethod authenticate(CustomerPaymentMethod customerPaymentMethod);
	
	CustomerPaymentMethod findById(Long id)throws IdNotFoundException;
	
	CustomerPaymentMethod validateCustomerPaymentMethod(Long id);
	
	Double getTotalCustomerPayment();
	
	boolean addCustomerPaymentMethod(CustomerPaymentMethod customerPaymentMethod);
	
	List<CustomerPaymentMethod> getCustomerAccountNumber(String accountNumber)throws CustomerPaymentMethodNotFoundException;
	

}
