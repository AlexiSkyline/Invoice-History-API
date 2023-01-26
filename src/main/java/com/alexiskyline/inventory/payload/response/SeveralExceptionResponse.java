package com.alexiskyline.inventory.payload.response;

import com.alexiskyline.inventory.exception.Error;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Getter @Setter
public class SeveralExceptionResponse {
    private String timeStamp;
    private int httpCode;
    private HttpStatus httpStatus;
    private String details;
    private List<Error> errors;
}