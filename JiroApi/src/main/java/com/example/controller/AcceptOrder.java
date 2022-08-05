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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/AcceptOrder")
public class AcceptOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JsonMappingException, JsonParseException {

    	String requestBody = request.getReader().lines().collect(Collectors.joining("\r\n"));
    	
    	ObjectMapper requestMapper = new ObjectMapper();
    	Order order = requestMapper.readValue(requestBody, Order.class);
    	
    	Ingredients ingredients = new Ingredients(order);
    	
    	ObjectMapper responseMapper = new ObjectMapper();
    	String responseBody = responseMapper.writeValueAsString(ingredients);
    	
    	response.setContentType("application/json; charset=utf-8");
    	
    	PrintWriter out;
    	out = response.getWriter();
        out.println(responseBody);
    }
}
