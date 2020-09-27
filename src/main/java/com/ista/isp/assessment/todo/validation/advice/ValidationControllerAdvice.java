package com.ista.isp.assessment.todo.validation.advice;

import com.ista.isp.assessment.todo.validation.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationControllerAdvice {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        //@formatter:off
        final List<FieldError> fieldErrors = Optional.of(ex.getBindingResult().getFieldErrors()).orElse(new ArrayList<>());
        final Map<String,String> errors = fieldErrors.stream()
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage, (s1, s2) -> s1 + " AND " + s2));
        //@formatter:on

        ErrorResponse apiErrorResponse = new ErrorResponse(errors);
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TransactionSystemException.class})
    public ResponseEntity<ErrorResponse> handleJPANotValidException(TransactionSystemException transactionSystemException) {
        transactionSystemException.printStackTrace();
        final Map<String, String> errors = new HashMap<>();
        errors.put("server_error", "Internal Server Error, take a look in logs");

        ErrorResponse apiErrorResponse = new ErrorResponse(errors);
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public void handleEntityNotFoundException(EntityNotFoundException ex) {
    }

}
