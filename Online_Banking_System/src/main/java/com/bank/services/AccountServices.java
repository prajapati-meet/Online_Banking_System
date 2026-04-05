package com.bank.services;

import java.util.*;
import com.bank.dto.CreateAccountRequest;
import com.bank.entity.Account;

public interface AccountServices
{
	Account createAccount(CreateAccountRequest  request);
	
	Account getAccount(String id);
	
	List<Account> getMyAccounts();
	
	
}
