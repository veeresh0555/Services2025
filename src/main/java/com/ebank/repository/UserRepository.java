package com.ebank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebank.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	//public UserEntity findByusernameAndpassword(String username, String password);

	public Optional<UserEntity> findByusernameAndPassword(String username, String password);

}
