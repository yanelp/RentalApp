package com.rentbike.model;

public class FamilyPromotion implements Promotion {

	private float averageDiscount;

	public FamilyPromotion(float averageDiscount) {
		super();
		this.setAverageDiscount(averageDiscount);
	}

	public float getAverageDiscount() {
		return averageDiscount;
	}

	public void setAverageDiscount(float averageDiscount) {
		this.averageDiscount = averageDiscount;
	}

	public float apply(Rental rental) {

		float totalPrice = 0;
		int bikesNumber = rental.getBikes().size();
		if ((bikesNumber >= 3) && (bikesNumber <= 5)) {
			float averageDiscount = getAverageDiscount() / 100;
			float discount = (float) (rental.getTotalPrice() * averageDiscount);
			totalPrice = rental.getTotalPrice() - discount;
		}

		return totalPrice;

	}

}
