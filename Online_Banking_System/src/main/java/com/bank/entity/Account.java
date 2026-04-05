package com.bank.entity;

import java.math.BigDecimal;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

@Entity
public class Account 
{
	@Id
	private String account_id;
	
	@Column(nullable=false , unique=true)
	private String account_number;
	
	@Column(nullable=false )
	private BigDecimal balance;
	
	@Column(nullable=false )
	private String currency;
		
	@Version
	@Column(nullable=false )
	private long version;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy="toAccount")
	@JsonIgnore
	private List<Transactions> receivedTransactions;
	
	@OneToMany(mappedBy="fromAccount")
	@JsonIgnore
	private List<Transactions> sentTransactions;
	
	public Account() {
		super();
	}

	public Account(String account_id, String account_number, BigDecimal balance, String currency, long version,
			User user) {
		super();
		this.account_id = account_id;
		this.account_number = account_number;
		this.balance = balance;
		this.currency = currency;
		this.version = version;
		this.user = user;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Transactions> getReceivedTransactions() {
		return receivedTransactions;
	}

	public void setReceivedTransactions(List<Transactions> receivedTransactions) {
		this.receivedTransactions = receivedTransactions;
	}

	public List<Transactions> getSentTransactions() {
		return sentTransactions;
	}

	public void setSentTransactions(List<Transactions> sentTransactions) {
		this.sentTransactions = sentTransactions;
	}

	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", account_number=" + account_number + ", balance=" + balance
				+ ", currency=" + currency + ", version=" + version + "]";
	}

	
	
	
}
