package com.bank.dto;

public class RegisterRequest 
{
	private String email;
	private String password_hash;
	
	public RegisterRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password_hash;
	}

	public void setPassword(String password_hash) {
		this.password_hash = password_hash;
	}
	
		
	
	
}
