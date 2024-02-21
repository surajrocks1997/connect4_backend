package com.theElite.connect4_backend.ExceptionHandler;

import com.theElite.connect4_backend.pojo.CustomException;
import com.theElite.connect4_backend.pojo.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorMessage(), ex.getStatusCode());
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }
}
