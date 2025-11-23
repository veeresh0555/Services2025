package com.ebank.dtos;

public record SuccessResponse<T>(T data) implements ApiResponse  {

}
