package com.nuracell.simplerestapi.exception;

public record ApiError(
        String errorCode,
        String description
) {
}
