package com.rentbike.model;

import java.util.Date;
import java.util.LinkedList;

public class Rental {

	private String clientID;
	private Date rentBegin;
	private LinkedList<Bike> bikes;
	private RentalType rentalType;
	private Integer rentalUnit;
	private float priceTotalRent;

	public Rental(String clientID, LinkedList<Bike> bikes,
			RentalType rentalType, Integer rentalUnit) {
		setClientID(clientID);
		setRentBegin(new Date());
		setBikes(bikes);
		setRentalUnit(rentalUnit);
		setRentalType(rentalType);

	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public Date getRentBegin() {
		return rentBegin;
	}

	public void setRentBegin(Date rentBegin) {
		this.rentBegin = rentBegin;
	}

	public LinkedList<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(LinkedList<Bike> bikes) {
		this.bikes = bikes;
	}

	public Integer getRentalUnit() {
		return rentalUnit;
	}

	public void setRentalUnit(Integer rentalUnit) {
		this.rentalUnit = rentalUnit;
	}

	public float getPriceTotalRent() {
		return priceTotalRent;
	}

	public void setPriceTotalRent(float priceTotalRent) {
		this.priceTotalRent = priceTotalRent;
	}

	public RentalType getRentalType() {
		return rentalType;
	}

	public void setRentalType(RentalType rentalType) {
		this.rentalType = rentalType;
	}

}