package com.nuracell.simplerestapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MessageExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(new ApiError(
                        e.getClass().getName(),
                        e.getMessage()
                ));
    }
}
