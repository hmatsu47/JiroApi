package com.example.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class Ingredients implements Serializable {
	@JsonProperty("noodle")
	private int noodle;
	
	@JsonProperty("charSiuPork")
	private int charSiuPork;
	
	@JsonProperty("vegetable")
	private Double vegetable;
	
	@JsonProperty("garlic")
	private Double garlic;
	
	@JsonProperty("fat")
	private Double fat;
	
	@JsonProperty("kaeshi")
	private Double kaeshi;
	
	// Order から必要食材に変換するためのコンストラクタ
	public Ingredients(Order order) {
		this.noodle = order.getNoodle();
		this.charSiuPork = order.getChaShuPork();
		this.vegetable = order.getVegetable();
		this.garlic = order.getGarlic();
		this.fat = order.getFat();
		this.kaeshi = order.getKaeshi();
	}
}
