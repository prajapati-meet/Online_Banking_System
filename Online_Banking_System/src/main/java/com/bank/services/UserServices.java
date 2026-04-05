package com.bank.services;

import com.bank.dto.LoginRequest;
import com.bank.dto.RegisterRequest;

public interface UserServices 
{
	String registerUser(RegisterRequest request);
	
	String loginUser(LoginRequest request);
}
