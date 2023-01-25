package com.alexiskyline.inventory.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Error {
    private final String filedName;
    private final String location;
    private final String message;
}