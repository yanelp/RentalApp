package com.rentbike.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.rentbike.model.Bike;
import com.rentbike.model.FamilyPromotion;
import com.rentbike.model.Promotion;
import com.rentbike.model.Rental;
import com.rentbike.model.RentalPerDay;
import com.rentbike.model.RentalPerHour;
import com.rentbike.model.RentalPerWeek;
import com.rentbike.model.RentalType;

public class App

{

	private LinkedList<Bike> availableBikes;
	private LinkedList<Bike> rentBikes;
	private ArrayList<Promotion> promotions;
	private ArrayList<Rental> rentals;

	public LinkedList<Bike> getAvailableBikes() {
		return availableBikes;
	}

	public void setAvailableBikes(LinkedList<Bike> availableBikes) {
		this.availableBikes = availableBikes;
	}

	public LinkedList<Bike> getRentBikes() {
		return rentBikes;
	}

	public void setRentBikes(LinkedList<Bike> rentBikes) {
		this.rentBikes = rentBikes;
	}

	public ArrayList<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(ArrayList<Promotion> promotions) {
		this.promotions = promotions;
	}

	public ArrayList<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(ArrayList<Rental> rentals) {
		this.rentals = rentals;
	}

	public static void main(String[] args) {
		App app = new App();
		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(new FamilyPromotion(30));
		Config config = new Config(20, promotions);
		app.initialize(config);
	}

	public void initialize(Config config) {

		LinkedList<Bike> bikes = config.getBikes();
		setPromotions(config.getPromotions());

		setAvailableBikes(bikes);
		setRentBikes(new LinkedList<Bike>());

		setRentals(new ArrayList<Rental>());
	}

	// Rental creation with stock control
	public String rent(String clientId, int bikes, String rentType,
			Integer rentalUnit) {

		/*
		 * 1. Verifico si alcanza stock 2. Si alcanza stock--> 2.1. Actualizo
		 * stock 2.2. Invoco a método para crear una Rental 2.3. Invoco a método
		 * para obtener el monto total del alquiler 2.4. Informo monto 3. Si no
		 * alcanza stock --> 3.1. Informo que no hay bicis disponibles.
		 */

		String message = new String();

		LinkedList<Bike> bikesToRent = new LinkedList<Bike>();

		if (getAvailableBikes().size() >= bikes) {

			for (int i = 0; i < bikes; i++) {

				// Stock Update

				Bike b = getAvailableBikes().remove();
				getRentBikes().add(b);

				bikesToRent.add(b);
			}

			// rental creation
			Rental rental = defineRental(clientId, bikesToRent, rentType,
					rentalUnit);
			getRentals().add(rental);

			// charge rent
			defineTotalPrice(rental);
			String price = String.valueOf(rental.getPriceTotalRent());

			message = "Total Rental: " + price;

		}

		else {
			message = "Sorry! there are not available bikes for the rental";

		}

		return message;
	}

	public Rental defineRental(String clientId, LinkedList<Bike> bikesToRent,
			String rentType, Integer rentalUnit) {

		RentalType rentalType;
		if (rentType == "perHour") {
			float pricePerBike = 5;
			rentalType = new RentalPerHour(pricePerBike);
		} else {
			if (rentType == "perDay") {
				float pricePerBike = 20;
				rentalType = new RentalPerDay(pricePerBike);
			} else {
				float pricePerBike = 60;
				rentalType = new RentalPerWeek(pricePerBike);
			}
		}

		Rental r = new Rental(clientId, bikesToRent, rentalType, rentalUnit);
		return r;
	}

	/*
	 * Functional decision: I assume that could exist more than one promotion.
	 * The app apply the promotion that return the minimum charge.
	 */

	public void defineTotalPrice(Rental rental) {

		float priceTotalBikes = (rental.getBikes().size())
				* rental.getRentalType().getPrice();
		float partialPrice = priceTotalBikes * rental.getRentalUnit();
		rental.setPriceTotalRent(partialPrice);

		ArrayList<Promotion> promotions = this.getPromotions();

		for (Iterator<Promotion> iterator = promotions.iterator(); iterator
				.hasNext();) {
			Promotion promotion = (Promotion) iterator.next();
			float priceWithPromotion = (float) promotion.applyPromotion(rental);
			if ((priceWithPromotion != 0)
					&& (priceWithPromotion < partialPrice)) {
				partialPrice = priceWithPromotion;

			}

		}
		rental.setPriceTotalRent(partialPrice);

	}

}