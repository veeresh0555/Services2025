package com.ebank.service;

import java.util.Optional;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ebank.dtos.UserMapper;
import com.ebank.dtos.UserRequest;
import com.ebank.dtos.UserResponse;
import com.ebank.entity.AccountEntity;
import com.ebank.entity.UserEntity;
import com.ebank.exception.DataCreationException;
import com.ebank.exception.ResourceNotfoundException;
import com.ebank.monitor.AccountCreationEvent;
import com.ebank.repository.AccountRepository;
import com.ebank.repository.UserRepository;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserMapper mapper;

	//
	/**
	 * 
	 * {@code} private final SecureRandom secureRandom = new SecureRandom(); Use the
	 * RandomGenerator interface for forward compatibility/modern style. The
	 * "SecureRandom" implementation is the appropriate standard for banking apps.
	 */
	private final RandomGenerator secureGenerator = RandomGeneratorFactory.of("SecureRandom").create();

	private static final Integer ACCOUNT_NUMBER_LENGTH = 12;

	@Override
	public UserResponse create(UserRequest request) {
		
		AccountCreationEvent event=new AccountCreationEvent();
		event.begin();
		try {
			var entity = mapper.toEntity(request);
			AccountEntity accountEntity = new AccountEntity();
			accountEntity.setBalance(0.0); // Initial balance
			accountEntity.setAccountNumber(generateUniqueAccountNumber());
			entity.setAccount(accountEntity);
			userRepository.save(entity);
			return mapper.toResponse(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataCreationException("Save operation failed: " + e.getMessage(), e // pass actual exception
			);
		}finally {
			event.end();
			event.commit();
		}
	}

	/**
	 * 
	 * @return
	 */

	private String generateUniqueAccountNumber() {
		String newAccountNumber = secureGenerator.ints(ACCOUNT_NUMBER_LENGTH, 0, 10).mapToObj(String::valueOf)
				.collect(Collectors.joining());
		if (accountRepository.findByAccountNumber(newAccountNumber).isPresent()) {
			return generateUniqueAccountNumber();
		}
		return newAccountNumber;
	}

	@Override
	public UserResponse loginUser(String username, String password) {

		try {
			Optional<UserEntity> isResponse = userRepository.findByusernameAndPassword(username, password);
			if (isResponse.isEmpty()) {
				throw new ResourceNotfoundException("Invalid username and password");
			}
			return mapper.toResponse(isResponse.get());
		} catch (DataCreationException e) {
			throw new DataCreationException("Login failed due to internal server error: " + e.getMessage(), e);
		}

	}

	/*
	 * private String generateUniqueAccountNumber() {
	 * 
	 * String uniqueIdPart = UUID.randomUUID().toString().replaceAll("_",
	 * "").substring(0, 8); String randomPart = String.format("%04d",
	 * secureRandom.nextInt(10000)); String newAccountNumber = uniqueIdPart +
	 * randomPart; if
	 * (accountRepository.findByAccountNumber(newAccountNumber).isPresent()) {
	 * return generateUniqueAccountNumber(); // Retry if collision occurs (rare) }
	 * return newAccountNumber; }
	 */

}
