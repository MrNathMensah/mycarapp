package com.onlinecarshop.mycarapp.service;

import com.onlinecarshop.mycarapp.exception.IdNotFoundException;
import com.onlinecarshop.mycarapp.pojo.OrderStatus;

public interface OrderStatusServiceInterface {
	
	OrderStatus authenticate(OrderStatus orderStatus);
	
	OrderStatus findById(Long id)throws IdNotFoundException;
	
	OrderStatus validateOrderStatus(Long id);
	
	boolean addOrderStatusDetails(OrderStatus orderStatus);
	

}
