package com.onlinecarshop.mycarapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.onlinecarshop.mycarapp.dao.ShipmentMethodDao;
import com.onlinecarshop.mycarapp.exception.IdNotFoundException;
import com.onlinecarshop.mycarapp.pojo.ShippmentMethod;

@Service
public class ShippmentMethodInterfaceImplementation implements ShippmentMethodInterface {
	
	@Autowired
	private ShipmentMethodDao dao;

	//Service Logic for Shipment Method Authentication
	@Override
	public ShippmentMethod authenticate(ShippmentMethod shippmentMethod) {
		ShippmentMethod shipment = new ShippmentMethod();
		shipment.setId(shippmentMethod.getId());
		shipment.setShippmentName(shippmentMethod.getShippmentName());
		
		Example<ShippmentMethod> shipmentExamp = Example.of(shipment);
		Optional<ShippmentMethod> option = dao.findOne(shipmentExamp);
		
		if(option.isPresent()) {
			return option.get();
		}
		return null;
	}

	//Service Logic for finding Shipment Method details by ID and help throw ID not found exception
	@Override
	public ShippmentMethod findById(Long id) throws IdNotFoundException {
		return dao.findById(id)
				.stream()
				.filter(shipmentMethod -> shipmentMethod.getId()== id)
				.findAny()
				.orElseThrow(()-> new IdNotFoundException("Shipment Method id not found"));
	}

	//Service Logic for Shipment Method Validation
	@Override
	public ShippmentMethod validateShipmentMethod(Long id) {
		Optional<ShippmentMethod> optional = dao.findById(id);
		 if(optional.isPresent()) {
			 return optional.get();
	}
		return null;
	}

	//Adding Shipment Method details using ID to authenticate
	@Override
	public boolean addShipmentInfo(ShippmentMethod shippmentMethod) {
		ShippmentMethod add = new ShippmentMethod();
		add.setId(shippmentMethod.getId());
		
		Example<ShippmentMethod> shipmentExamp = Example.of(add);
		Optional<ShippmentMethod> option = dao.findOne(shipmentExamp);
		if(option.isPresent()) {
			return true;
		}
		return false;
	}

}
