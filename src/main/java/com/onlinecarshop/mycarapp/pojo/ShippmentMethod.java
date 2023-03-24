package com.onlinecarshop.mycarapp.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ShippmentMethod {
	
	@Id
	@GeneratedValue
	private Long id;
	private String shippmentName;
	private Double shippingPrice;
	
	@OneToMany(mappedBy="shippmentMethod")
	private List<CustomerOrder> customerOrder;

	public ShippmentMethod() {
		super();
	}

	public ShippmentMethod(Long id, String shippmentName, Double shippingPrice, List<CustomerOrder> customerOrder) {
		super();
		this.id = id;
		this.shippmentName = shippmentName;
		this.shippingPrice = shippingPrice;
		this.customerOrder = customerOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShippmentName() {
		return shippmentName;
	}

	public void setShippmentName(String shippmentName) {
		this.shippmentName = shippmentName;
	}

	public Double getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(Double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public List<CustomerOrder> getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(List<CustomerOrder> customerOrder) {
		this.customerOrder = customerOrder;
	}
	
}
