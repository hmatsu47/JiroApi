package com.example.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class Order implements Serializable {
	private String ticketLabel;
	private String lotOption;
	private Call call;
	
	public Order(String ticketLabel, String lotOption, Call call) {
		this.ticketLabel = ticketLabel;
		this.lotOption = ticketLabel;
		this.call = call;
	}
}
