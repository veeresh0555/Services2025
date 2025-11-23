package com.ebank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebank.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>{

	public Optional<AccountEntity> findByAccountNumber(String newAccountNumber);

}
