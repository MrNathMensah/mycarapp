package com.onlinecarshop.mycarapp.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CustomerPaymentMethod {
	
	@Id
	@GeneratedValue
	private Long id;
	private String paymentType;
	private Double amountPaid;
	private String accountNumber;
	private Date expiryDate;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	
	@OneToOne(mappedBy="customerPaymentMethod")
	private CustomerOrder customerOrder;

	public CustomerPaymentMethod() {
		super();
	}

	public CustomerPaymentMethod(Long id, String paymentType, Double amountPaid, String accountNumber, Date expiryDate,
			Customer customer, CustomerOrder customerOrder) {
		super();
		this.id = id;
		this.paymentType = paymentType;
		this.amountPaid = amountPaid;
		this.accountNumber = accountNumber;
		this.expiryDate = expiryDate;
		this.customer = customer;
		this.customerOrder = customerOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	
}
