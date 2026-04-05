package com.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Beneficiaries {

	@Id
	private String beneficiaries_id;
	
	@Column(nullable=false )
	private String name;
	
	@Column(nullable=false )
	private String account_number;
	
	@Column(nullable=false, columnDefinition="TINYINT(1)")
	private Boolean verified;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Beneficiaries() {
		super();
	}

	public Beneficiaries(String beneficiaries_id, String name, String account_number, Boolean verified, User user) {
		super();
		this.beneficiaries_id = beneficiaries_id;
		this.name = name;
		this.account_number = account_number;
		this.verified = verified;
		this.user = user;
	}

	public String getBeneficiaries_id() {
		return beneficiaries_id;
	}

	public void setBeneficiaries_id(String beneficiaries_id) {
		this.beneficiaries_id = beneficiaries_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Beneficiaries [beneficiaries_id=" + beneficiaries_id + ", name=" + name + ", account_number="
				+ account_number + ", verified=" + verified + "]";
	}

	
	
	
	
	

}
