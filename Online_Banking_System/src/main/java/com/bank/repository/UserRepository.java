package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.User;

public interface UserRepository extends JpaRepository<User, String>
{
	Optional<User> findByEmail(String email);
	
	long count(); 
}
