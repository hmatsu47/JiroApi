package com.example.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order implements Serializable {
	@JsonProperty("ticketLabel")
	private String ticketLabel;

	@JsonProperty("lotOption")
	private String lotOption;

	@JsonProperty("yasai")
	private String yasai;

	@JsonProperty("ninniku")
	private String ninniku;

	@JsonProperty("abura")
	private String abura;

	@JsonProperty("karame")
	private String karame;

	private static Map<String, Integer> noodleAmountsMap = new HashMap<>();
	private static Map<String, Boolean> lotOptionSelectableMap = new HashMap<>();
	private static Map<String, Integer> lotOptionMap = new HashMap<>();
	private static Map<String, Integer> chaSiuPorkMap = new HashMap<>();
	private static Map<String, Double> amountMap = new HashMap<>();

	static {
		// チケット別の標準麺量
		noodleAmountsMap.put("ラーメン", 300);
		noodleAmountsMap.put("ぶたラーメン", 300);
		noodleAmountsMap.put("ぶたダブルラーメン", 300);
		noodleAmountsMap.put("大ラーメン", 450);
		noodleAmountsMap.put("ぶた入り大ラーメン", 450);
		noodleAmountsMap.put("ぶたダブル大ラーメン", 450);
		// ロットオプション指定可能なチケットか？
		lotOptionSelectableMap.put("ラーメン", true);
		lotOptionSelectableMap.put("ぶたラーメン", true);
		lotOptionSelectableMap.put("ぶたダブルラーメン", true);
		lotOptionSelectableMap.put("大ラーメン", false);
		lotOptionSelectableMap.put("ぶた入り大ラーメン", false);
		lotOptionSelectableMap.put("ぶたダブル大ラーメン", false);
		// ロットオプション（麺量）
		lotOptionMap.put("少なめ", 200);
		lotOptionMap.put("半分", 150);
		lotOptionMap.put("1/3", 100);
		// チャーシューの枚数
		chaSiuPorkMap.put("ラーメン", 2);
		chaSiuPorkMap.put("ぶたラーメン", 4);
		chaSiuPorkMap.put("ぶたダブルラーメン", 8);
		chaSiuPorkMap.put("大ラーメン", 2);
		chaSiuPorkMap.put("ぶた入り大ラーメン", 4);
		chaSiuPorkMap.put("ぶたダブル大ラーメン", 8);
		// コールオプション（コール別の量）
		amountMap.put("指定なし", 1.0);
		amountMap.put("抜き", 0.0);
		amountMap.put("少なめ", 0.5);
		amountMap.put("マシ", 2.0);
		amountMap.put("マシマシ", 3.0);
	}
	// ロットオプション無指定の表記
	final String noLotOption = "なし";

	// テストコード用のコンストラクタ
	public Order(String ticketLabel, String lotOption, String yasai, String ninniku, String abura, String karame) {

		this.ticketLabel = ticketLabel;
		this.lotOption = lotOption;
		this.yasai = yasai;
		this.ninniku = ninniku;
		this.abura = abura;
		this.karame = karame;
	}

	// Jackson で JSON →オブジェクト変換するためのデフォルトコンストラクタ
	public Order() {

	}

	private void checkTicketLabel() throws RuntimeException {
		if (this.ticketLabel == null) {
			throw new RuntimeException("チケットの指定に誤りがあります：null");
		}
		if (noodleAmountsMap.get(this.ticketLabel) == null) {
			throw new RuntimeException("チケットの指定に誤りがあります：" + this.ticketLabel);
		}
	}

	private void checkLotOption() throws RuntimeException {
		if (this.lotOption == null) {
			throw new RuntimeException("ロットオプションの指定に誤りがあります：null");
		}
		if (this.lotOption.equals(noLotOption)) {
			return;
		}
		if (!lotOptionSelectableMap.get(this.ticketLabel)) {
			throw new RuntimeException("大サイズの場合ロットオプションは指定できません");
		}
		if (lotOptionMap.get(this.lotOption) == null) {
			throw new RuntimeException("ロットオプションの指定に誤りがあります：" + this.lotOption);
		}
	}

	private void checkAmountExpression(String expression) {
		if (expression == null) {
			throw new RuntimeException("コールの指定に誤りがあります：null");
		}
		if (amountMap.get(expression) == null) {
			throw new RuntimeException("コールの指定に誤りがあります：" + expression);
		}
	}

	private Double getAmount(String expression) {
		return amountMap.get(expression);
	}

	/*
	 * このオブジェクトは Jackson からの変換に使用する
	 * 変換するタイミングではバリデーションできないため
	 * 変換後にバリデーションするメソッドとして用意
	 */
	public void validate() throws RuntimeException {
		checkTicketLabel();
		checkLotOption();
		checkAmountExpression(this.yasai);
		checkAmountExpression(this.ninniku);
		checkAmountExpression(this.abura);
		checkAmountExpression(this.karame);
	}

	public int getNoodle() throws RuntimeException {
		if (lotOptionSelectableMap.get(ticketLabel) && !lotOption.equals(noLotOption)) {
			return lotOptionMap.get(lotOption);
		}
		return noodleAmountsMap.get(ticketLabel);
	}

	public int getChaSiuPork() throws RuntimeException {
		return chaSiuPorkMap.get(ticketLabel);
	}

	public Double getVegetable() {
		return getAmount(this.yasai);
	}

	public Double getGarlic() {
		return getAmount(this.ninniku);
	}

	public Double getFat() {
		return getAmount(this.abura);
	}

	public Double getKaeshi() {
		return getAmount(this.karame);
	}
}
