package com.harvices.project.expense_tracker_api;
import com.harvices.project.expense_tracker_api.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyTaken.class)
    public ResponseEntity<Object> handleEmailAlreadyTaken(EmailAlreadyTaken ex){
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailOrPasswordNotValid.class)
    public ResponseEntity<Object> handleEmailOrPasswordNotValid(EmailOrPasswordNotValid ex){
        return buildErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFound ex){
        return buildErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseNotFound.class)
    public ResponseEntity<Object> handleExpenseNotFound(ExpenseNotFound ex){
        return buildErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnAuthorizedExpense.class)
    public ResponseEntity<Object> handleUnAuthorizeExpense(ExpenseNotFound ex){
        return buildErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmailNotFound.class)
    public ResponseEntity<Object> handleEmailNotFound(EmailNotFound ex){
        return buildErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(StartDateOrEndDateCannotBeNull.class)
    public ResponseEntity<Object> handleStartDateOrEndDateCannotBeNull(StartDateOrEndDateCannotBeNull ex){
        return buildErrorResponse(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(InvalidFilterOption.class)
    public ResponseEntity<Object> handleInvalidFilterOption(InvalidFilterOption ex){
        return buildErrorResponse(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }
    private ResponseEntity<Object> buildErrorResponse(String message,HttpStatus status){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}

