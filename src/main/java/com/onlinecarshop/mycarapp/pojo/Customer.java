package com.onlinecarshop.mycarapp.pojo;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private Long id;
	private String customerName;
	private String customeEmail;
	private String customerNumber;
	private String customerPassword;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy="customer")
	private List<CustomerPaymentMethod> customerPaymentMethod;
	
	@OneToMany(mappedBy="customer")
	private List<CustomerOrder> customerOrder;

	public Customer() {
		super();
	}

	public Customer(Long id, String customerName, String customeEmail, String customerNumber, String customerPassword,
			Address address, List<CustomerPaymentMethod> customerPaymentMethod, List<CustomerOrder> customerOrder) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customeEmail = customeEmail;
		this.customerNumber = customerNumber;
		this.customerPassword = customerPassword;
		this.address = address;
		this.customerPaymentMethod = customerPaymentMethod;
		this.customerOrder = customerOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomeEmail() {
		return customeEmail;
	}

	public void setCustomeEmail(String customeEmail) {
		this.customeEmail = customeEmail;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<CustomerPaymentMethod> getCustomerPaymentMethod() {
		return customerPaymentMethod;
	}

	public void setCustomerPaymentMethod(List<CustomerPaymentMethod> customerPaymentMethod) {
		this.customerPaymentMethod = customerPaymentMethod;
	}

	public List<CustomerOrder> getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(List<CustomerOrder> customerOrder) {
		this.customerOrder = customerOrder;
	}

	
	
}
