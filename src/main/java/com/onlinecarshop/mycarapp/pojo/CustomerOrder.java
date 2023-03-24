package com.onlinecarshop.mycarapp.pojo;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class CustomerOrder {
	
	@Id
	@GeneratedValue
	private Long id;
	private Date orderDate;
	private Double orderPrice;
	private Integer orderQuantity;
	
	@Embedded
	private Address address;
	
	@Embedded
	private CarBasicDetails carBasicDetails;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="shipping_method_id")
	private ShippmentMethod shippmentMethod;
	
	@ManyToOne
	@JoinColumn(name="order_status_id")
	private OrderStatus orderStatus;
	
	@OneToOne
	@JoinColumn(name="payment_method_id")
	private CustomerPaymentMethod customerPaymentMethod;

	public CustomerOrder() {
		super();
	}

	public CustomerOrder(Long id, Date orderDate, Double orderPrice, Integer orderQuantity, Address address,
			CarBasicDetails carBasicDetails, Car car, Customer customer, ShippmentMethod shippmentMethod,
			OrderStatus orderStatus, CustomerPaymentMethod customerPaymentMethod) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderPrice = orderPrice;
		this.orderQuantity = orderQuantity;
		this.address = address;
		this.carBasicDetails = carBasicDetails;
		this.car = car;
		this.customer = customer;
		this.shippmentMethod = shippmentMethod;
		this.orderStatus = orderStatus;
		this.customerPaymentMethod = customerPaymentMethod;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CarBasicDetails getCarBasicDetails() {
		return carBasicDetails;
	}

	public void setCarBasicDetails(CarBasicDetails carBasicDetails) {
		this.carBasicDetails = carBasicDetails;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ShippmentMethod getShippmentMethod() {
		return shippmentMethod;
	}

	public void setShippmentMethod(ShippmentMethod shippmentMethod) {
		this.shippmentMethod = shippmentMethod;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public CustomerPaymentMethod getCustomerPaymentMethod() {
		return customerPaymentMethod;
	}

	public void setCustomerPaymentMethod(CustomerPaymentMethod customerPaymentMethod) {
		this.customerPaymentMethod = customerPaymentMethod;
	}
	
}
