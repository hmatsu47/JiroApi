package com.example.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class Toppings implements Serializable {
	private Double vegetables;
	private Double garlic;
	private Double fat;
	private Double kaeshi;
	
	public Toppings(Call call) {
		
	}
}
