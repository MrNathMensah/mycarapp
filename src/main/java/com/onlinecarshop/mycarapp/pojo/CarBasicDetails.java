package com.onlinecarshop.mycarapp.pojo;

import javax.persistence.Embeddable;

@Embeddable
public class CarBasicDetails {
	
	private String carBrand;
	private String carModel;
	private String yearModel;
	private String carColor;
	private String fuelCapacity;
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getYearModel() {
		return yearModel;
	}
	public void setYearModel(String yearModel) {
		this.yearModel = yearModel;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public String getFuelCapacity() {
		return fuelCapacity;
	}
	public void setFuelCapacity(String fuelCapacity) {
		this.fuelCapacity = fuelCapacity;
	}
	
	

}
