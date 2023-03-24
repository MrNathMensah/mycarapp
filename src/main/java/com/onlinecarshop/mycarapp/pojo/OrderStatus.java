package com.onlinecarshop.mycarapp.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OrderStatus {
	
	@Id
	@GeneratedValue
	private Long id;
	private String status;
	
	@OneToMany(mappedBy="orderStatus")
	private List<CustomerOrder> customerOrder;

	public OrderStatus() {
		super();
	}

	public OrderStatus(Long id, String status, List<CustomerOrder> customerOrder) {
		super();
		this.id = id;
		this.status = status;
		this.customerOrder = customerOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CustomerOrder> getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(List<CustomerOrder> customerOrder) {
		this.customerOrder = customerOrder;
	}
	
	

}
