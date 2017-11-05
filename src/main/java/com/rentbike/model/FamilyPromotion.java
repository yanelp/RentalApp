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

	/*
	 * En caso de aplicar a la promoción se queda con la que retorna el menor
	 * pago. Se toma esta desición para permitir extender el modelo con más
	 * promociones.
	 */

	public float applyPromotion(Rental rental) {

		float totalPrice = 0;
		int bikesNumber = rental.getBikes().size();
		if ((bikesNumber >= 3) && (bikesNumber <= 5)) {
			float averageDiscount = getAverageDiscount() / 100;
			float discount = (float) (rental.getPriceTotalRent() * averageDiscount);
			totalPrice = rental.getPriceTotalRent() - discount;
		}

		return totalPrice;

	}

}
