package com.rentbike.model;

public class RentalType {

	private float price;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public RentalType(float pricePerBike) {
		this.setPrice(pricePerBike);

	}

}
