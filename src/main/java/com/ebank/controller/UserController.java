package com.ebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebank.dtos.ApiResponse;
import com.ebank.dtos.SuccessResponse;
import com.ebank.dtos.UserRequest;
import com.ebank.dtos.UserResponse;
import com.ebank.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/saveUser")
	public ResponseEntity<ApiResponse> createUser(@RequestBody UserRequest request){
		UserResponse response=userService.create(request);
		//return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(response));
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
	
	
	

}
