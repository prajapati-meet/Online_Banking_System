package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, String> {

}
