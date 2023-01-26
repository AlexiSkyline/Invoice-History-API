package com.alexiskyline.inventory.exception;

import lombok.Getter;

@Getter
public class DuplicateResourceException extends RuntimeException {
    private final String resourceName;
    private final String filedName;
    private final String filedValue;

    public DuplicateResourceException(String resourceName, String filedName, String filedValue) {
        super(String.format( "The '%s' field with the value '%s', is already in use with another '%s'.",
                resourceName, filedName, filedValue ));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.filedValue = filedValue;
    }
}