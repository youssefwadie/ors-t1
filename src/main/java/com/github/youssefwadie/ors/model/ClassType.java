package com.github.youssefwadie.ors.model;


public enum ClassType {
	FIRST_CLASS("First Class"),
	BUSINESS_CLASS("Business Class"),
	ECONOMY_CLASS("Economy Class");
	
	private final String description;
	private ClassType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	
}
