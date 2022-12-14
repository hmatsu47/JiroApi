package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Ingredients;
import com.example.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/AcceptOrder")
public class AcceptOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
    	response.setContentType("application/json");
    	PrintWriter out;
    	out = response.getWriter();
    	
    	try {
	    	String requestBody = request.getReader().lines().collect(Collectors.joining(""));
	    	
	    	ObjectMapper requestMapper = new ObjectMapper();
	    	Order order = requestMapper.readValue(requestBody, Order.class);
	    	// Jackson で受け取った値をバリデーション
	    	order.validate();
	    	// 注文内容から必要な食材の量を割り出す
	    	Ingredients ingredients = new Ingredients(order);
	    	
	    	ObjectMapper responseMapper = new ObjectMapper();
	    	String responseBody = responseMapper.writeValueAsString(ingredients);
	    	
	        out.println(responseBody);
	        out.flush();
    	} catch(Exception e) {
    		e.printStackTrace();
    		// 組み込みエラー処理のトリガを避けるためにあえて sendError() を使わず setStatus() する
    		response.setStatus(400);
    		out.println("{\"message\":\"" + e.getMessage() + "\"}");
    		out.flush();
    	}
    }
}
