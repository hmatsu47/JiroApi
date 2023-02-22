package com.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("注文内容が正しい食材の構成に変換されること（バリデーション別実装なので異常系は除外）")
class IngredientsTest {

	@ParameterizedTest
	@DisplayName("小ラーメンはロットオプションで麺量が変わり、コールでトッピングの量が変わる")
	@CsvSource({
	    "ラーメン, なし, 指定なし, 抜き, 少なめ, マシ, 300, 2, 1.0, 0.0, 0.5, 2.0",
	    "ぶたラーメン, 少なめ, 抜き, 少なめ, マシ, マシマシ, 200, 4, 0.0, 0.5, 2.0, 3.0",
	    "ぶたダブルラーメン, 半分, 少なめ, マシ, マシマシ, 指定なし, 150, 8, 0.5, 2.0, 3.0, 1.0",
	    "ラーメン, 1/3, マシ, マシマシ, 指定なし, 少なめ, 100, 2, 2.0, 3.0, 1.0, 0.5"
	})
	void convertSmall(
			String ticketLabel, String lotOption, String yasai, String ninniku, String abura, String karame,
			int noodle, int chaShuPork, Double vegetable, Double garlic, Double fat, Double kaeshi) {
		convert(ticketLabel, lotOption, yasai, ninniku, abura, karame, noodle,  chaShuPork, vegetable, garlic, fat, kaeshi);
	}
	
	@ParameterizedTest
	@DisplayName("大ラーメンはロットオプションなしで麺量は固定、コールでトッピングの量が変わる")
	@CsvSource({
	    "大ラーメン, なし, 指定なし, 抜き, 少なめ, マシ, 450, 2, 1.0, 0.0, 0.5, 2.0",
	    "ぶた入り大ラーメン, なし, 抜き, 少なめ, マシ, マシマシ, 450, 4, 0.0, 0.5, 2.0, 3.0",
	    "ぶたダブル大ラーメン, なし, 少なめ, マシ, マシマシ, 指定なし, 450, 8, 0.5, 2.0, 3.0, 1.0"
	})
	void convertLarge(
			String ticketLabel, String lotOption, String yasai, String ninniku, String abura, String karame,
			int noodle, int chaShuPork, Double vegetable, Double garlic, Double fat, Double kaeshi) {
		convert(ticketLabel, lotOption, yasai, ninniku, abura, karame, noodle,  chaShuPork, vegetable, garlic, fat, kaeshi);
	}
	
	void convert(String ticketLabel, String lotOption, String yasai, String ninniku, String abura, String karame,
			int noodle, int chaShuPork, Double vegetable, Double garlic, Double fat, Double kaeshi) {
		// Order に実装されている変換メソッドのテストを兼ねる
		Order order = new Order(ticketLabel, lotOption, yasai, ninniku, abura, karame);
		Ingredients ingredients = new Ingredients(order);
		assertEquals(ingredients.getNoodle(), noodle);
		assertEquals(ingredients.getChaSiuPork(), chaShuPork);
		assertEquals(ingredients.getVegetable(), vegetable);
		assertEquals(ingredients.getGarlic(), garlic);
		assertEquals(ingredients.getFat(), fat);
		assertEquals(ingredients.getKaeshi(), kaeshi);
	}
}
