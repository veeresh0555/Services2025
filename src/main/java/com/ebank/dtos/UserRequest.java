package com.ebank.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
		@NotBlank(message = "Name Required")
		String username,
		@Email(message = "Invalid Email")
		String email,
		String gender, 
		String password,
		String mobilenumber,
		AddressRequest address
		) {

}
