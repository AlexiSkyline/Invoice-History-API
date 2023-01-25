package com.alexiskyline.inventory.controller;

import com.alexiskyline.inventory.exception.Error;
import com.alexiskyline.inventory.exception.ResourceNotFoundException;
import com.alexiskyline.inventory.payload.SeveralExceptionResponse;
import com.alexiskyline.inventory.payload.SingleExceptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@AllArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<Error> errors = new LinkedList<>();
        result.getFieldErrors().forEach( error -> {
            String message = this.messageSource.getMessage( error, Locale.forLanguageTag( "US" ) );
            errors.add(
                    Error.builder()
                    .filedName(error.getField())
                    .location("JSON-Body")
                    .message(message)
                            .build() );
        });
        SeveralExceptionResponse responseBody = SeveralExceptionResponse.builder()
                .timeStamp(new Date().toString())
                .httpCode(status.value())
                .httpStatus((HttpStatus) status)
                .details("Invalid request body. Please check the format and try again")
                .errors(errors)
                .build();
        return new ResponseEntity<>( responseBody, status );
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<SingleExceptionResponse> resourceNotFoundException(ResourceNotFoundException exception) {
        Error error = Error.builder()
                .filedName(exception.getFiledName())
                .location("PathVariable")
                .message(exception.getMessage())
                .build();
        SingleExceptionResponse response = SingleExceptionResponse.builder()
                .timeStamp(new Date().toString())
                .httpCode(NOT_FOUND.value())
                .httpStatus(NOT_FOUND)
                .details(exception.getDetails())
                .error(error)
                .build();
        return new ResponseEntity<>(response, NOT_FOUND);
    }
}