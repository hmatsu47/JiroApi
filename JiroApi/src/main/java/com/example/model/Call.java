package com.example.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class Call implements Serializable {
	private String yasai;
	private String ninniku;
	private String abura;
	private String karame;
	
	public Call(String yasai, String ninniku, String abura, String karame) {
		this.yasai = yasai;
		this.ninniku = ninniku;
		this.abura = abura;
		this.karame = karame;
	}
}
