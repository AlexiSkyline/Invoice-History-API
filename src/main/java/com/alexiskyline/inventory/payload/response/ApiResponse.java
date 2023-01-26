package com.alexiskyline.inventory.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter @Setter
public class ApiResponse<T> {
    private final String timeStamp;
    private final int httpCode;
    private final HttpStatus httpStatus;
    private final String message;
    private T data;
}