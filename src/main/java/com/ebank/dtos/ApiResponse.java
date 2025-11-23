package com.ebank.dtos;

public sealed interface ApiResponse permits SuccessResponse,ErrorResponse {}
