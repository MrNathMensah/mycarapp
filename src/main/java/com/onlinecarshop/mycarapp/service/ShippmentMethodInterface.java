package com.onlinecarshop.mycarapp.service;

import com.onlinecarshop.mycarapp.exception.IdNotFoundException;
import com.onlinecarshop.mycarapp.pojo.ShippmentMethod;

public interface ShippmentMethodInterface {
	
	ShippmentMethod authenticate(ShippmentMethod shippmentMethod);
	
	ShippmentMethod findById(Long id)throws IdNotFoundException;
	
	ShippmentMethod validateShipmentMethod(Long id);
	
	boolean addShipmentInfo(ShippmentMethod shippmentMethod);

}
