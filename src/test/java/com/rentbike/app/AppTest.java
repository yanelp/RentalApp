package com.rentbike.app;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import com.rentbike.model.Bike;
import com.rentbike.model.FamilyPromotion;
import com.rentbike.model.Promotion;
import com.rentbike.model.Rental;

public class AppTest {

	private static App app;

	@Before
	public void configApp() {
		app = new App();
	}

	@Test
	public void testRentWithoutStock() {

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		FamilyPromotion familyPromotion = new FamilyPromotion(30);
		promotions.add(familyPromotion);

		Config config = new Config(0, promotions);

		app.initialize(config);

		String actualResult = app.rent("29558573", 1, "perHour", 1);
		String expectedResult = "Sorry! there are not available bikes for the rental";

		assertTrue(actualResult.equals(expectedResult));

	}

	@Test
	public void testRentWithSameStock() {

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		FamilyPromotion familyPromotion = new FamilyPromotion(30);
		promotions.add(familyPromotion);

		Config config = new Config(2, promotions);
		app.initialize(config);

		String actualResult = app.rent("29558573", 2, "perDay", 1);
		float chargeExpected = new Float(40.00);
		String expectedResult = "Total Rental: " + chargeExpected;

		assertTrue(actualResult.equals(expectedResult));

	}

	@Test
	public void testRentWithFamilyPromotion() {

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		FamilyPromotion familyPromotion = new FamilyPromotion(30);
		promotions.add(familyPromotion);

		Config config = new Config(20, promotions);
		app.initialize(config);

		String actualResult = app.rent("29558573", 3, "perWeek", 2);
		float chargeExpected = new Float(252.00);
		String expectedResult = "Total Rental: " + chargeExpected;

		assertTrue(actualResult.equals(expectedResult));

	}

	@Test
	public void testRentNotApplyFamilyPromotion() {

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		FamilyPromotion familyPromotion = new FamilyPromotion(30);
		promotions.add(familyPromotion);

		Config config = new Config(20, promotions);
		app.initialize(config);

		String actualResult = app.rent("29558573", 6, "perHour", 3);
		float chargeExpected = new Float(90.00);
		String expectedResult = "Total Rental: " + chargeExpected;

		assertTrue(actualResult.equals(expectedResult));

	}

	@Test
	public void testNegativeBikeCount() {

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		FamilyPromotion familyPromotion = new FamilyPromotion(30);
		promotions.add(familyPromotion);

		Config config = new Config(-1, promotions);
		app.initialize(config);

		String actualResult = app.rent("29558573", 2, "perHour", 4);
		String expectedResult = "Sorry! there are not available bikes for the rental";

		assertTrue(actualResult.equals(expectedResult));

	}

	@Test
	public void testEmptyPromotions() {

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();

		Config config = new Config(10, promotions);
		app.initialize(config);

		String actualResult = app.rent("29558573", 4, "perHour", 4);
		float chargeExpected = new Float(80.00);
		String expectedResult = "Total Rental: " + chargeExpected;

		assertTrue(actualResult.equals(expectedResult));

	}

	@Test
	public void testNoPromotionWithMinimalRentalPrice() {

		Promotion promotion = new Promotion() {

			@Override
			public float apply(Rental rental) {
				return rental.getTotalPrice();
			}
		};

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(promotion);

		Config config = new Config(20, promotions);
		app.initialize(config);

		String actualResult = app.rent("29558573", 4, "perHour", 5);

		float chargeExpected = new Float(100.00);
		String expectedResult = "Total Rental: " + chargeExpected;

		assertTrue(actualResult.equals(expectedResult));
	}

	@Test
	public void testApplyMultiplePromotions() {

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(new FamilyPromotion(30));

		/*
		 * if the rental is for more than two units of time, apply 10% discount
		 * of total price
		 */
		Promotion TwoDaysPromotion = new Promotion() {

			@Override
			public float apply(Rental rental) {
				float totalPrice = 0;
				if (rental.getTimeUnit() > 2) {
					float discount = (float) (rental.getTotalPrice() * 0.10);
					totalPrice = rental.getTotalPrice() - discount;
				}
				return totalPrice;
			}
		};

		promotions.add(TwoDaysPromotion);

		Config config = new Config(20, promotions);
		app.initialize(config);

		String actualResult = app.rent("27295585736", 4, "perDay", 3);

		float chargeExpected = new Float(168.00);
		String expectedResult = "Total Rental: " + chargeExpected;

		assertTrue(actualResult.equals(expectedResult));
	}

	@Test
	public void testRentalInformation() {
		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		FamilyPromotion familyPromotion = new FamilyPromotion(30);
		promotions.add(familyPromotion);

		Config config = new Config(10, promotions);
		app.initialize(config);

		app.rent("29558573", 2, "perHour", 4);
		Rental actualRental = app.getRentals().remove(0);

		Date rentalDate = actualRental.getDate();
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		String rentalBenig = formatoFecha.format(rentalDate);
		String today = formatoFecha.format(new Date());

		assertTrue(actualRental.getClientID() == "29558573");
		assertEquals(rentalBenig, today);
		assertTrue(actualRental.getBikes().size() == 2);
		assertTrue(actualRental.getType().getPrice() == 5);
		assertTrue(actualRental.getTimeUnit() == 4);
		assertTrue(actualRental.getTotalPrice() == 40.00);

	}

	@Test
	public void testBikeInformation() {

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();

		Config config = new Config(10, promotions);
		app.initialize(config);

		app.rent("29558573", 1, "perHour", 4);

		Bike bike = app.getRentBikes().removeFirst();

		assertTrue(bike.getModel().equals("BikeName0"));
		assertTrue(bike.getDescription().equals("bikeDetails0"));

	}
}
