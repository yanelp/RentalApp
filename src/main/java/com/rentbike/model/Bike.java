package com.rentbike.model;

public class Bike {

	private String model;
	private String description;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Bike(String model, String description) {
		this.setModel(model);
		this.setDescription(description);

	}

}
