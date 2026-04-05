package com.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transactions 
{
	@Id
	private String transaction_id;
	
	@Column(nullable=false )
	private String type;
	
	@Column(nullable=false )
	private BigDecimal amount;
	
	@Column(nullable=false )
	private BigDecimal reference_amount;
	
	@Column(nullable=false )
	private String status;
	
	@Column(nullable=false )
	private LocalDateTime created_at;
	
	@ManyToOne
	@JoinColumn(name="from_account")
	private Account fromAccount;
	
	@ManyToOne
	@JoinColumn(name="to_account")
	private Account toAccount;
	
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transactions(String transaction_id, String type, BigDecimal amount, BigDecimal reference_amount, String status,
			LocalDateTime created_at, Account fromAccount, Account toAccount) {
		super();
		this.transaction_id = transaction_id;
		this.type = type;
		this.amount = amount;
		this.reference_amount = reference_amount;
		this.status = status;
		this.created_at = created_at;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;

	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getReference_amount() {
		return reference_amount;
	}

	public void setReference_amount(BigDecimal reference_amount) {
		this.reference_amount = reference_amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	
	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", type=" + type + ", amount=" + amount
				+ ", reference_amount=" + reference_amount + ", status=" + status + ", created_at=" + created_at + "]";
	}

	
	
	
}
