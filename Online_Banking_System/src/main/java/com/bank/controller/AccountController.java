package com.bank.controller;


import org.springframework.http.HttpStatus;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.CreateAccountRequest;
import com.bank.entity.Account;
import com.bank.services.AccountServices;

@RestController
@RequestMapping("/accounts")
public class AccountController 
{
	private AccountServices accServ;

	public AccountController(AccountServices accServ) {
		super();
		this.accServ = accServ;
	}
	
	@PostMapping
	public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest request) 
	{
		try 
		{
			Account created=accServ.createAccount(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(created);
		}catch(RuntimeException e)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
	
	@GetMapping("/my-accounts")
    public ResponseEntity<?> getMyAccounts() 
	{
        try {
            List<Account> accounts = accServ.getMyAccounts();
            return ResponseEntity.ok(accounts);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAccount(@PathVariable String id)
	{
		try
		{
			Account acc= accServ.getAccount(id);
			return ResponseEntity.ok(acc);
		}catch(RuntimeException e)
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
		
	}
	
	 
	

}
