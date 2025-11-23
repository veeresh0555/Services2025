package com.ebank.dtos;

public record UserResponse(Long id, String username, String email, String gender, String password,String mobilenumber,AddressResponse address,AccountNumberResponse account) {
}
