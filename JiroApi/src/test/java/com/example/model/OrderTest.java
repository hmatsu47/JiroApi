package com.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("注文内容のバリデーションが想定通り成功／失敗すること")
class OrderTest {

	@ParameterizedTest
	@DisplayName("正しい注文内容のバリデーションが成功すること")
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
	@DisplayName("不正なチケットのバリデーションが失敗すること")
	@CsvSource({
	    "タンメン, なし, 指定なし, 指定なし, 指定なし, 指定なし",
	    "冷やし中華, なし, 指定なし, 指定なし, 指定なし, 指定なし"
	})
	void failTicketValidation(String ticketLabel, String lotOption, String yasai, String ninniku, String abura, String karame) {
		Order order = new Order(ticketLabel, lotOption, yasai, ninniku, abura, karame);
		assertThrows(RuntimeException.class, () -> order.validate());
	}

	@ParameterizedTest
	@DisplayName("不正なロットオプションのバリデーションが失敗すること")
	@CsvSource({
	    "ラーメン, あり, 指定なし, 指定なし, 指定なし, 指定なし",
	    "大ラーメン, 少なめ, 指定なし, 指定なし, 指定なし, 指定なし",
	    "ぶた入り大ラーメン, 半分, 指定なし, 指定なし, 指定なし, 指定なし",
	    "ぶたダブル大ラーメン, 1/3, 指定なし, 指定なし, 指定なし, 指定なし"
	})
	void failLotOptionValidation(String ticketLabel, String lotOption, String yasai, String ninniku, String abura, String karame) {
		Order order = new Order(ticketLabel, lotOption, yasai, ninniku, abura, karame);
		assertThrows(RuntimeException.class, () -> order.validate());
	}
	
	@ParameterizedTest
	@DisplayName("不正なコールのバリデーションが失敗すること")
	@CsvSource({
	    "ラーメン, なし, 山盛り, 指定なし, 指定なし, 指定なし",
	    "ラーメン, なし, 指定なし, 3粒, 指定なし, 指定なし",
	    "ラーメン, なし, 指定なし, 指定なし, ベトベト, 指定なし",
	    "ラーメン, なし, 指定なし, 指定なし, 指定なし, 濃いめ"
	})
	void failCallValidation(String ticketLabel, String lotOption, String yasai, String ninniku, String abura, String karame) {
		Order order = new Order(ticketLabel, lotOption, yasai, ninniku, abura, karame);
		assertThrows(RuntimeException.class, () -> order.validate());
	}

	@ParameterizedTest
	@DisplayName("注文内容にnullが含まれるときにバリデーションが失敗すること")
	@CsvSource({
	    ", なし, 指定なし, 指定なし, 指定なし, 指定なし",
	    "ラーメン,, 指定なし, 指定なし, 指定なし, 指定なし",
	    "ラーメン, なし,, 指定なし, 指定なし, 指定なし",
	    "ラーメン, なし, 指定なし,, 指定なし, 指定なし",
	    "ラーメン, なし, 指定なし, 指定なし,, 指定なし",
	    "ラーメン, なし, 指定なし, 指定なし, 指定なし,"
	})
	void failNullValidation(String ticketLabel, String lotOption, String yasai, String ninniku, String abura, String karame) {
		Order order = new Order(ticketLabel, lotOption, yasai, ninniku, abura, karame);
		assertThrows(RuntimeException.class, () -> order.validate());
	}

}
