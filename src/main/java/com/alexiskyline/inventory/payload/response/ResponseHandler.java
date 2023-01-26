package com.alexiskyline.inventory.payload.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ResponseHandler {
    public static <T> ResponseEntity<ApiResponse<T>> responseBuild(HttpStatus httpStatus, String message, T data) {
        ApiResponse<T> responseBody = ApiResponse.<T>builder()
                .timeStamp(new Date().toString())
                .httpCode(httpStatus.value())
                .httpStatus(httpStatus)
                .message(message)
                .data(data)
                .build();
        return new ResponseEntity<>( responseBody, httpStatus );
    }
}