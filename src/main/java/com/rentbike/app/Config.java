package com.rentbike.app;

import java.util.ArrayList;
import java.util.LinkedList;

import com.rentbike.model.Bike;
import com.rentbike.model.Promotion;

public class Config {

    private LinkedList<Bike> bikes;
    private ArrayList<Promotion> promotions;
    
    
	public Config(Integer bikesNumber, ArrayList<Promotion> appPromotions) {
  	
		//Define a set of bikes
		LinkedList<Bike> bikes = new LinkedList<Bike>();
     	 for (int i = 0; i < bikesNumber; i++) {
     		 	Bike bike = new Bike("BikeName"+i, "bikeDetails"+i);
     		 	bikes.add(bike);
		}
		
		setBikes(bikes);
		
		//define promotions
		setPromotions(appPromotions);
		
		
		
   	}

	
	public LinkedList<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(LinkedList<Bike> bikes) {
		this.bikes = bikes;
	}


	public ArrayList<Promotion> getPromotions() {
		return promotions;
	}


	public void setPromotions(ArrayList<Promotion> promotions) {
		this.promotions = promotions;
	}
		
   	
}
