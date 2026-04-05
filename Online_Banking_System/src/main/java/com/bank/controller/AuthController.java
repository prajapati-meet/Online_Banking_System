package com.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.AuthResponse;
import com.bank.dto.LoginRequest;
import com.bank.dto.RegisterRequest;

import com.bank.security.JwtUtil;
import com.bank.services.UserServices;

@RestController
@RequestMapping("/auth")
public class AuthController 
{
	private UserServices userServ;

	public AuthController(UserServices userServ) {
		super();
		this.userServ = userServ;
	}
	
	@PostMapping("/register")
	public AuthResponse register(@RequestBody RegisterRequest request)
	{
		String mssg= userServ.registerUser(request);
		return new AuthResponse(mssg);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
	{
		String mssg=userServ.loginUser(request);
		if(mssg.equals("Login Successfull"))
		{
			String token= JwtUtil.generateToken(request.getEmail());
			return ResponseEntity.ok(new AuthResponse(token));
		}else {
			return  ResponseEntity.status(401)
	                .body(new AuthResponse("Invalid Credentials"));
		}
	}
	
}
