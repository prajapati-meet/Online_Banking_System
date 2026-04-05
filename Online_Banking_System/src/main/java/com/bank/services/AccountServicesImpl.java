package com.bank.services;



import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bank.dto.CreateAccountRequest;
import com.bank.entity.Account;
import com.bank.entity.User;
import com.bank.repository.AccountRepository;
import com.bank.repository.UserRepository;

@Service
public class AccountServicesImpl implements AccountServices
{
	
	UserRepository userRepo;
	AccountRepository accRepo;

	public AccountServicesImpl(UserRepository userRepo, AccountRepository accRepo) {
		super();
		this.userRepo = userRepo;
		this.accRepo = accRepo;
	}
	
	@Override
	public Account createAccount(CreateAccountRequest request) {
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		
		if(auth ==null || !auth.isAuthenticated())
		{
			throw new RuntimeException("Unauthorized : No authenticated user found!");
		}
		
		String email=auth.getName();
		User user=userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found. Please register first."));
		
		Account acc=new Account();
		long count= accRepo.count()+1;
		String id=String.format("ACC_%02d", count);
		
		acc.setAccount_id(id);
		acc.setAccount_number(setAccountNumber(id));
		acc.setBalance(request.getBalance());
		acc.setCurrency("INR");
		acc.setUser(user);
		
		return accRepo.save(acc);
	}

	private String setAccountNumber(String id) {
		return id+System.currentTimeMillis();
	}

	@Override
	public Account getAccount(String id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth==null || !auth.isAuthenticated())
		{
			throw new RuntimeException("Unauthorized: No authentication user found!");
		}
		
		String email=auth.getName();
		User loggedInUser= userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found"));
		
		Account account=accRepo.findById(id).orElseThrow(()-> new RuntimeException("Account Not Found"));
		
		if(!account.getUser().getUser_id().equals(loggedInUser.getUser_id()))
		{
			throw new RuntimeException("Unauthorized access to account!");
		}
		
		return account;
	}

	@Override
	public List<Account> getMyAccounts() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if (auth == null || !auth.isAuthenticated()) {
			 throw new RuntimeException("Unauthorized: No authenticated user found");
	     }
	     
		 String email = auth.getName();
		 User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		 return accRepo.findByUser(user);
	}



	

}
