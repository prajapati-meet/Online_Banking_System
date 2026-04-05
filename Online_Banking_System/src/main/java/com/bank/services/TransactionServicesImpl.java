package com.bank.services;

import org.springframework.stereotype.Service;

import com.bank.entity.Transactions;
import com.bank.repository.TransactionRepository;

@Service
public class TransactionServicesImpl implements TransactionServices
{
	private TransactionRepository trepo;
	
	public TransactionServicesImpl(TransactionRepository trepo) {
		super();
		this.trepo = trepo;
	}

	@Override
	public void saveTransaction(Transactions t) {
		trepo.save(t);
		
	}

	
}
