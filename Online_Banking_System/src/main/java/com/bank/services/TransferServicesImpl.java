package com.bank.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bank.dto.TransferRequest;
import com.bank.repository.AccountRepository;
import com.bank.repository.UserRepository;

import jakarta.transaction.Transactional;

import com.bank.entity.Account;
import com.bank.entity.Transactions;
import com.bank.entity.User;

@Service
public class TransferServicesImpl implements TransferServices
{
	
	private AccountRepository accRepo;
	private TransactionServices tserv;
	private UserRepository userRepo;

	
	public TransferServicesImpl(AccountRepository accRepo, TransactionServices tserv, UserRepository usaeRepo) {
		super();
		this.accRepo = accRepo;
		this.tserv = tserv;
		this.userRepo = usaeRepo;
	}

	@Transactional
	public String transfer(TransferRequest request) 
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth==null || !auth.isAuthenticated())
		{
			return "Unauthorized : Please login first!";
		}
		
		String email=auth.getName();
		User loggedInUser= userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found!"));
		Account sender=accRepo.findById(request.getFromAccount()).orElseThrow(()->new RuntimeException("Sender Account Not Found"));
		Account receiver=accRepo.findById(request.getToAccount()).orElseThrow(()->new RuntimeException("Receiver Account Not Found"));
		
		if(!sender.getUser().getUser_id().equals(loggedInUser.getUser_id()))
		{
			return "Invalid Sender Account";
		}
		
		if (sender.getAccount_id().equals(receiver.getAccount_id())) {
            return "Sender and receiver cannot be same";
        }
		
		BigDecimal amount=request.getAmount();
		
		
		if(sender.getBalance().compareTo(amount)<0)
		{
			return "Transaction Failed. Insufficient Balance";
		}
		
		sender.setBalance(sender.getBalance().subtract(amount));
		receiver.setBalance(receiver.getBalance().add(amount));
		
		accRepo.save(sender);
		accRepo.save(receiver);
		
		Transactions t= new Transactions(
				"TNX"+sender.getAccount_id()+ receiver.getAccount_id()+System.currentTimeMillis(),
				"TRANSFER",
				amount,
				sender.getBalance(),
				"SUCCESS",
				LocalDateTime.now(),
				sender,
				receiver
				);
		
		tserv.saveTransaction(t);
		return "Transaction Successful";
	}

}