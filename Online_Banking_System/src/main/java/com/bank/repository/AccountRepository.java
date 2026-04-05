package com.bank.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.Account;
import com.bank.entity.User;


public interface AccountRepository extends JpaRepository<Account, String> {

	List<Account> findByUser(User user);
		
}
