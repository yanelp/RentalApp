package com.rentbike.model;

import java.util.Date;
import java.util.LinkedList;

public class Rental {

	private String clientID;
	private Date date;
	private LinkedList<Bike> bikes;
	private RentalType type;
	private Integer timeUnit;
	private float totalPrice;

	public Rental(String clientID, LinkedList<Bike> bikes,
			RentalType rentalType, Integer rentalUnit) {
		setClientID(clientID);
		setDate(new Date());
		setBikes(bikes);
		setTimeUnit(rentalUnit);
		setType(rentalType);

	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date rentBegin) {
		this.date = rentBegin;
	}

	public LinkedList<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(LinkedList<Bike> bikes) {
		this.bikes = bikes;
	}

	public Integer getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(Integer rentalUnit) {
		this.timeUnit = rentalUnit;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float priceTotalRent) {
		this.totalPrice = priceTotalRent;
	}

	public RentalType getType() {
		return type;
	}

	public void setType(RentalType rentalType) {
		this.type = rentalType;
	}

}