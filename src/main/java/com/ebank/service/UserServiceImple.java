package com.ebank.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebank.controller.UserController;
import com.ebank.dtos.AccountNumberResponse;
import com.ebank.dtos.AccountRequest;
import com.ebank.dtos.UserMapper;
import com.ebank.dtos.UserRequest;
import com.ebank.dtos.UserResponse;
import com.ebank.entity.AccountEntity;
import com.ebank.entity.TransactionEntity;
import com.ebank.entity.UserEntity;
import com.ebank.exception.DataCreationException;
import com.ebank.exception.ResourceNotfoundException;
import com.ebank.monitor.AccountCreationEvent;
import com.ebank.repository.AccountRepository;
import com.ebank.repository.TransactionRepository;
import com.ebank.repository.UserRepository;
import com.ebank.utils.GenericEnum;

@Service
public class UserServiceImple implements UserService {
	
	Logger logger=LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private UserMapper mapper;

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
		logger.info("Enter UserServiceImpl and create method {} ");

		AccountCreationEvent event = new AccountCreationEvent();
		event.begin();
		try {
			var entity = mapper.toEntity(request);
			AccountEntity accountEntity = new AccountEntity();
			accountEntity.setBalance(0.0); // Initial balance
			accountEntity.setAccountNumber(generateUniqueAccountNumber());
			entity.setAccount(accountEntity);
			userRepository.save(entity);
			logger.info("Exit UserServiceImpl and create method {} ");
			return mapper.toResponse(entity);
			
		} catch (Exception e) {
			logger.error("Save operation failed:{} {} " , e.getMessage(), e);
			throw new DataCreationException("Save operation failed: " + e.getMessage(), e // pass actual exception
			);
		} finally {
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
		logger.info("generated Account Number: {} ",newAccountNumber);
		if (accountRepository.findByAccountNumber(newAccountNumber).isPresent()) {// fall back -negative case
			return generateUniqueAccountNumber();
		}
		logger.info("Final generated Account Number: {} ",newAccountNumber);
		
		return newAccountNumber;
	}

	@Override
	public UserResponse loginUser(String username, String password) {
		logger.info("Enter UserServiceImpl and loginUser Method: {} {} ",username,password);
		try {
			Optional<UserEntity> isResponse = userRepository.findByusernameAndPassword(username, password);
			if (isResponse.isEmpty()) {
				throw new ResourceNotfoundException("Invalid username and password");
			}
			logger.info("Exit UserServiceImpl and loginUser Method");
			return mapper.toResponse(isResponse.get());
		} catch (DataCreationException e) {
			logger.error("Login failed due to internal server error: {} {} " , e.getMessage(), e);
			throw new DataCreationException("Login failed due to internal server error: " + e.getMessage(), e);
		}

	}

	@Override
	public AccountNumberResponse deposit(AccountRequest request) {
		logger.info("Enter UserServiceImpl and deposit Method");
		System.out.println(""+request.accountNumber());
		try {
			Optional<AccountEntity> isAccount=accountRepository.findByAccountNumber(request.accountNumber());
			
			if (isAccount.isPresent()) {
				AccountEntity entity = mapper.toAccountEntity(request);
				entity.setId(isAccount.get().getId());
				/**
				 * Calculation for deposit Existing @{Amount+Request deposit Amount}
				 * 
				 * Stream APi provides intermediate terminal operations - SUM 
				 * 1st->10
				 * 2nd->10 => existing Amount+new Amount =>10+10=>20
				 * 
				 */
				Double sumOfbalnce = Stream.of(isAccount.get().getBalance(),request.balance()).mapToDouble(Double::doubleValue).sum();
				entity.setBalance(sumOfbalnce);
				entity=accountRepository.save(entity);
				/**
				 * Transaction table should be update/create
				 */
				TransactionEntity transEntity=new TransactionEntity();
				transEntity.setTransref("123xyz");//Reference Number generate Randomnumbers
				transEntity.setAccount(entity);
				transEntity.setStatus(GenericEnum.Success.toString());
				transEntity.setAmount(request.balance());
				transEntity.setRemainingBal(sumOfbalnce);
				transEntity.setTransType(GenericEnum.deposit.toString());
				transEntity.setTransactioDate(LocalDateTime.now());
				transactionRepository.save(transEntity);
				logger.info("Exit UserServiceImpl and deposit Method");
				return mapper.toResponse(entity);
			} else {
				logger.warn("User Not Found: {}");
				throw new ResourceNotfoundException("User Not Found: ");
			}
		} catch (Exception e) {
			logger.error("Internal server Error ! amount deposit fail: {} {} " , e.getMessage(), e);
			throw new DataCreationException("Internal server Error ! amount deposit fail: " + e.getMessage(), e);
		}

	}

	@Override
	public AccountNumberResponse viewBalance(String acccountNumber) {
		logger.info("Enter UserServiceImpl and viewBalance Method");
		Optional<AccountEntity> isbankAccount=accountRepository.findByAccountNumber(acccountNumber);
		if(isbankAccount.isPresent()) {
			return mapper.toResponse(isbankAccount.get());
			
		}else {
			throw new ResourceNotfoundException("Account Number Not Found or Wrong Account Number "+acccountNumber);
		}
	}

}
