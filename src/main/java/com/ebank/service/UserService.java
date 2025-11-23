package com.ebank.service;

import com.ebank.dtos.UserRequest;
import com.ebank.dtos.UserResponse;

public interface UserService {

	public UserResponse create(UserRequest request);

	public UserResponse loginUser(String username, String password);

}
