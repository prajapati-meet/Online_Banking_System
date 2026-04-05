package com.bank.dto;

import java.math.BigDecimal;

public class TransferRequest 
{
	private String toAccount;
	private String fromAccount;
	private BigDecimal amount;

	public TransferRequest() {
		super();
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOtp() {
		return null;
	}
	
	
}
