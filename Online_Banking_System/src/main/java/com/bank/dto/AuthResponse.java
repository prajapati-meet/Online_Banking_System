package com.bank.dto;

public class AuthResponse 
{
	private String message;

	public AuthResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
