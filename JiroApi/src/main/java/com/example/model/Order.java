package com.example.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class Order implements Serializable {
	private String ticketLabel;
	private String lotOption;
	private Call call;
	private static Map<String, Integer> noodleAmountsMap = new HashMap<>();
	private static Map<String, Boolean> lotOptionSelectableMap = new HashMap<>();
	private static Map<String, Integer> lotOptionMap = new HashMap<>();
	private static Map<String, Integer> chaShuPorkMap = new HashMap<>();
	
	static {
		noodleAmountsMap.put("ラーメン", 300);
		noodleAmountsMap.put("ぶたラーメン", 300);
		noodleAmountsMap.put("ぶたダブルラーメン", 300);
		noodleAmountsMap.put("大ラーメン", 450);
		noodleAmountsMap.put("ぶた入り大ラーメン", 450);
		noodleAmountsMap.put("ぶたダブル大ラーメン", 450);
		
		lotOptionSelectableMap.put("ラーメン", true);
		lotOptionSelectableMap.put("ぶたラーメン", true);
		lotOptionSelectableMap.put("ぶたダブルラーメン", true);
		lotOptionSelectableMap.put("大ラーメン", false);
		lotOptionSelectableMap.put("ぶた入り大ラーメン", false);
		lotOptionSelectableMap.put("ぶたダブル大ラーメン", false);
		
		lotOptionMap.put("少なめ", 200);
		lotOptionMap.put("半分", 150);
		lotOptionMap.put("1/3", 100);
		
		chaShuPorkMap.put("ラーメン", 2);
		chaShuPorkMap.put("ぶたラーメン", 4);
		chaShuPorkMap.put("ぶたダブルラーメン", 8);
		chaShuPorkMap.put("大ラーメン", 2);
		chaShuPorkMap.put("ぶた入り大ラーメン", 4);
		chaShuPorkMap.put("ぶたダブル大ラーメン", 8);
	}
	
	public Order(String ticketLabel, String lotOption, Call call) {
		
		this.ticketLabel = ticketLabel;
		this.lotOption = ticketLabel;
		this.call = call;
	}

	private void checkTicketLabel() throws RuntimeException {
		if (noodleAmountsMap.get(ticketLabel) == null) {
			throw new RuntimeException("チケットの指定に誤りがあります：" + ticketLabel);
		}
	}

	private void checkLotOption() throws RuntimeException {
		checkTicketLabel();
		if (!lotOptionSelectableMap.get(ticketLabel)) {
			throw new RuntimeException("大サイズの場合ロットオプションは指定できません");
		}
		if (lotOption != null && lotOptionMap.get(lotOption) == null) {
			throw new RuntimeException("ロットオプションの指定に誤りがあります：" + lotOption);
		}
	}
	
	public int getNoodleAmounts() throws RuntimeException {
		checkLotOption();
		if (lotOptionSelectableMap.get(ticketLabel) && lotOption != null) {
			return lotOptionMap.get(lotOption);
		}
		return noodleAmountsMap.get(ticketLabel);
	}
	
	public int getChaShuPork() throws RuntimeException {
		checkTicketLabel();
		return chaShuPorkMap.get(ticketLabel);
	}
}
