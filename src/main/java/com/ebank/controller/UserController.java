package com.ebank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebank.dtos.AccountNumberResponse;
import com.ebank.dtos.AccountRequest;
import com.ebank.dtos.ApiResponse;
import com.ebank.dtos.SuccessResponse;
import com.ebank.dtos.UserRequest;
import com.ebank.dtos.UserResponse;
import com.ebank.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
	
	Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/saveUser")
	public ResponseEntity<ApiResponse> createUser(@RequestBody UserRequest request){
		logger.info("Enter UserController and create User Method {} ");
		UserResponse response=userService.create(request);
		logger.info("Exit UserController and create User Method {} ");
		return new ResponseEntity<>(new SuccessResponse<>(response),HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@GetMapping("/login")
	public ResponseEntity<ApiResponse> loginUser(@RequestParam("username") String username, @RequestParam("password") String password){
		UserResponse response=userService.loginUser(username,password);
		return new ResponseEntity<>(new SuccessResponse<>(response),HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	
	@PostMapping("/deposit")
	public ResponseEntity<ApiResponse> depositAmount(@RequestBody AccountRequest request){
		logger.info("Enter UserController and depositAmount Method {} ");
		AccountNumberResponse response=userService.deposit(request);
		logger.info("Exit UserController and depositAmount Method {} ");
		return new ResponseEntity<>(new SuccessResponse<>(response),HttpStatus.OK);
	}
	
	
	@GetMapping("/viewBalance")
	public ResponseEntity<ApiResponse> viewBalance(@RequestParam("accountNumber") String acccountNumber){
		AccountNumberResponse response=userService.viewBalance(acccountNumber);
		return new ResponseEntity<>(new SuccessResponse<>(response),HttpStatus.OK);
	}
	
	
	
	

	
	
	
	
	
	

}
