package com.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderTest {

	@ParameterizedTest
	@DisplayName("01.正常パターンのバリデーション成功")
	@CsvSource({
	    "ラーメン, なし, 指定なし, 抜き, 少なめ, マシ",
	    "ぶたラーメン, 少なめ, 抜き, 少なめ, マシ, マシマシ",
	    "ぶたダブルラーメン, 半分, 少なめ, マシ, マシマシ, 指定なし",
	    "ラーメン, 1/3, マシ, マシマシ, 指定なし, 少なめ",
	    "大ラーメン, なし, 指定なし, 抜き, 少なめ, マシ",
	    "ぶた入り大ラーメン, なし, 抜き, 少なめ, マシ, マシマシ",
	    "ぶたダブル大ラーメン, なし, 少なめ, マシ, マシマシ, 指定なし"
	})
	void passValidation(String ticketLabel, String lotOption, String yasai, String ninniku, String abura, String karame) {
		Order order = new Order(ticketLabel, lotOption, yasai, ninniku, abura, karame);
		assertDoesNotThrow(() -> order.validate());
	}

	@ParameterizedTest
	@DisplayName("02.異常パターンのバリデーション失敗")
	@CsvSource({
	    "中ラーメン, なし, 指定なし, 抜き, 少なめ, マシ",
	    "ラーメン, あり, 抜き, 少なめ, マシ, マシマシ",
	    "ぶたラーメン, 少なめ, 山盛り, マシ, マシマシ, 指定なし",
	    "ぶたダブルラーメン, 半分, マシ, 3粒, 指定なし, 抜き",
	    "ラーメン, 1/3, マシマシ, 指定なし, ゼロ, 少なめ",
	    "大ラーメン, なし, 指定なし, 抜き, 少なめ, 本気",
	    "null, なし, 指定なし, 抜き, 少なめ, マシ",
	    "ラーメン, null, 抜き, 少なめ, マシ, マシマシ",
	    "ぶた入り大ラーメン, なし, null, マシ, マシマシ, 指定なし",
	    "ぶたダブル大ラーメン, なし, マシ, null, 指定なし, 抜き",
	    "ラーメン, 1/3, マシマシ, 指定なし, null, 少なめ",
	    "大ラーメン, なし, 指定なし, 抜き, 少なめ, null",
	    "ぶた入り大ラーメン, 少なめ, 抜き, 少なめ, マシ, マシマシ"
	})
	void failValidation(String ticketLabel, String lotOption, String yasai, String ninniku, String abura, String karame) {
		Order order = new Order(
				(ticketLabel.equals("null") ? null : ticketLabel), 
				(lotOption.equals("null") ? null : lotOption), 
				(yasai.equals("null") ? null : yasai), 
				(ninniku.equals("null") ? null : ninniku), 
				(abura.equals("null") ? null : abura), 
				(karame.equals("null") ? null : karame));
		assertThrows(RuntimeException.class, () -> order.validate());
	}

}
