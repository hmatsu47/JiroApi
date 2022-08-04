package com.example.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class Ingredients implements Serializable {
	private MainIngredients mainIngredients;
	private Toppings toppings;
	
	public Ingredients(MainIngredients mainIngredients, Toppings toppings) {
		this.mainIngredients = mainIngredients;
		this.toppings = toppings;
	}
}
