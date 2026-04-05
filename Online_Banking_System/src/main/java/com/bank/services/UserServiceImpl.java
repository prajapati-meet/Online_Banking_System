package com.bank.services;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.dto.LoginRequest;
import com.bank.dto.RegisterRequest;
import com.bank.entity.User;
import com.bank.repository.UserRepository;

@Service
public class UserServiceImpl implements UserServices
{
	private UserRepository userRepo;
	private BCryptPasswordEncoder passEnc;
	
	public UserServiceImpl(UserRepository userRepo, BCryptPasswordEncoder passEnc) {
		super();
		this.userRepo = userRepo;
		this.passEnc = passEnc;
	}

	@Override
	public String registerUser(RegisterRequest request) {
		
		User user = new User();
		long count= userRepo.count()+1;
		
		String userId= String.format("user_%02d", count);
		
		user.setUser_id(userId);
		user.setEmail(request.getEmail());
		
		user.setPassword_hash(passEnc.encode(request.getPassword()));
		
		user.setRole("Customer");
		user.setMfa_enabled(false);
		user.setCreated_at(LocalDateTime.now());
		userRepo.save(user);
		
		return "User Registration Successfully";
	}
	

	@Override
	public String loginUser(LoginRequest request) 
	{
		User user= userRepo.findByEmail(request.getEmail()).orElse(null);;
		
		if(user==null)
		{
			return "User Not Found";
		}
		
		boolean match = passEnc.matches(request.getPassword(), user.getPassword_hash());
		
		if(!match)
		{
			return "Invalid Password";
		}
		
		return "Login Successfull";
		
	}

	
	

}
