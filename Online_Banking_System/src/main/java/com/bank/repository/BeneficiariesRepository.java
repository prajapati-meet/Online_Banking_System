package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.Beneficiaries;

public interface BeneficiariesRepository extends JpaRepository<Beneficiaries, String>{

}
