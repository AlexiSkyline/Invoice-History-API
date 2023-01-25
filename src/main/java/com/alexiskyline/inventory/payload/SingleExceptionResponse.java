package com.alexiskyline.inventory.payload;

import com.alexiskyline.inventory.exception.Error;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter @Setter
public class SingleExceptionResponse {
    private String timeStamp;
    private int httpCode;
    private HttpStatus httpStatus;
    private String details;
    private Error error;
}