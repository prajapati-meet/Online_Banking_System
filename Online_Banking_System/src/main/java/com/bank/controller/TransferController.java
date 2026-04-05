package com.bank.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.TransferRequest;
import com.bank.services.TransferServices;

@RestController
@RequestMapping("/transfer")
public class TransferController
{
	TransferServices tserv;

	public TransferController(TransferServices tserv) {
		super();
		this.tserv = tserv;
	}
	
	@PostMapping
	public ResponseEntity<String> transfer(@RequestBody TransferRequest request )
	{
		 String mssg=tserv.transfer(request);
		 
		 if (mssg.equals("Transaction Successful")) {
	            return ResponseEntity.ok(mssg);
	        } else if (mssg.contains("Unauthorized")) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mssg);
	        } else if (mssg.contains("Invalid sender") || mssg.contains("same")) {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(mssg);
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mssg);
	        }
	}
}
