package com.example.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class MainIngredients implements Serializable {
	private int noodleAmounts;
	private int charSiuPorks;
	
	public MainIngredients(String ticketLabel, String lotOption) {
		
	}
}
