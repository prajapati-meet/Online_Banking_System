package com.bank.entity;

import java.time.LocalDateTime;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User 
{
	@Id
	private String user_id;

	@Column(nullable =false, unique=true )
	private String email;
	
	@Column(nullable=false )
	@JsonIgnore
	private String password_hash;
	
	@Column(nullable=false )
	private String role;
	
	@Column(nullable=false, columnDefinition ="TINYINT(1)" )
	private Boolean mfa_enabled;

	@Column(nullable=false )
	private LocalDateTime created_at;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Account> acc;
	
	
	public User() {
		super();
	}


	public User(String user_id, String email, String password_hash, String role, Boolean mfa_enabled,
			LocalDateTime created_at, List<Account> acc) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.password_hash = password_hash;
		this.role = role;
		this.mfa_enabled = mfa_enabled;
		this.created_at = created_at;
		this.acc = acc;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword_hash() {
		return password_hash;
	}


	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Boolean getMfa_enabled() {
		return mfa_enabled;
	}


	public void setMfa_enabled(Boolean mfa_enabled) {
		this.mfa_enabled = mfa_enabled;
	}


	public LocalDateTime getCreated_at() {
		return created_at;
	}


	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}


	public List<Account> getAcc() {
		return acc;
	}


	public void setAcc(List<Account> acc) {
		this.acc = acc;
	}


	//for avoiding recursion loop in toString we removed mapped objects
	//for security we removed hashpass from toString method
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", email=" + email +  ", role=" + role
				+ ", mfa_enabled=" + mfa_enabled + ", created_at=" + created_at + "]";
	}
	
	
	
	
}
