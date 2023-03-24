package com.onlinecarshop.mycarapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.onlinecarshop.mycarapp.dao.OrderStatusDao;
import com.onlinecarshop.mycarapp.exception.IdNotFoundException;
import com.onlinecarshop.mycarapp.pojo.OrderStatus;

@Service
public class OrderStatusServiceInterfaceImplementation implements OrderStatusServiceInterface {
	
	@Autowired
	private OrderStatusDao dao;

	//Service Logic for Order Status Authentication
	@Override
	public OrderStatus authenticate(OrderStatus orderStatus) {
		OrderStatus order = new OrderStatus();
		order.setId(orderStatus.getId());
		order.setStatus(orderStatus.getStatus());
		
		Example<OrderStatus> orderExamp = Example.of(order);
		Optional<OrderStatus> option = dao.findOne(orderExamp);
		
		if(option.isPresent()) {
			return option.get();
		}
		return null;
	}

	//Service Logic for finding Order Status by ID and help throw ID not found exception
	@Override
	public OrderStatus findById(Long id) throws IdNotFoundException {
		return dao.findById(id)
				.stream()
				.filter(orderStatus -> orderStatus.getId()== id)
				.findAny()
				.orElseThrow(()-> new IdNotFoundException("Order Status id not found"));
	}

	//Service Logic for Order Status Validation
	@Override
	public OrderStatus validateOrderStatus(Long id) {
		Optional<OrderStatus> optional = dao.findById(id);
		 if(optional.isPresent()) {
			 return optional.get();
	}
		return null;
	}

	//Adding Order Status details using ID to authenticate
	@Override
	public boolean addOrderStatusDetails(OrderStatus orderStatus) {
		OrderStatus add = new OrderStatus();
		add.setId(orderStatus.getId());
		
		Example<OrderStatus> orderExamp = Example.of(add);
		Optional<OrderStatus> option = dao.findOne(orderExamp);
		if(option.isPresent()) {
			return true;
		}
		return false;
	}

}
