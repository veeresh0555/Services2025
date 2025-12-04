package com.ebank.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/***
 * 
 */
@Entity
@Table(name = "transaction")
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "status")
	private String status;

	@Column(name = "amount")
	private Double amount;

	@CreationTimestamp
	@Column(name = "transaction_date")
	private LocalDateTime transactioDate;

	@Column(name = "reference")
	private String transref;

	@Column(name = "trans_type")
	private String transType;

	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private AccountEntity account; // fk
	
	@Column(name = "remaining_balance")
	private Double remainingBal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransactioDate() {
		return transactioDate;
	}

	public void setTransactioDate(LocalDateTime transactioDate) {
		this.transactioDate = transactioDate;
	}

	public String getTransref() {
		return transref;
	}

	public void setTransref(String transref) {
		this.transref = transref;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Double getRemainingBal() {
		return remainingBal;
	}

	public void setRemainingBal(Double remainingBal) {
		this.remainingBal = remainingBal;
	}
	

}
