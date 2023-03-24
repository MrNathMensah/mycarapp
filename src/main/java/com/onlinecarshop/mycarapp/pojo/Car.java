package com.onlinecarshop.mycarapp.pojo;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Car {
	
	@Id
	@GeneratedValue
	private Long id;
	private Integer quantityInStock;
	private Double carPrice;
	private String description;
	private String carImage;
	
	@Embedded
	private CarBasicDetails carBasicDetails;
	
	@OneToMany(mappedBy="car")
	private List<CustomerOrder> customerOrder;

	public Car() {
		super();
	}

	public Car(Long id, Integer quantityInStock, Double carPrice, String description, String carImage,
			CarBasicDetails carBasicDetails, List<CustomerOrder> customerOrder) {
		super();
		this.id = id;
		this.quantityInStock = quantityInStock;
		this.carPrice = carPrice;
		this.description = description;
		this.carImage = carImage;
		this.carBasicDetails = carBasicDetails;
		this.customerOrder = customerOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public Double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(Double carPrice) {
		this.carPrice = carPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCarImage() {
		return carImage;
	}

	public void setCarImage(String carImage) {
		this.carImage = carImage;
	}

	public CarBasicDetails getCarBasicDetails() {
		return carBasicDetails;
	}

	public void setCarBasicDetails(CarBasicDetails carBasicDetails) {
		this.carBasicDetails = carBasicDetails;
	}

	public List<CustomerOrder> getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(List<CustomerOrder> customerOrder) {
		this.customerOrder = customerOrder;
	}
	
}
