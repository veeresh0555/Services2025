package com.ebank.dtos;

public record ErrorResponse(Integer errorCodeValue,String errorCode,String message) implements ApiResponse {

}
