package com.alexiskyline.inventory.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String filedName;
    private final String filedValue;

    public ResourceNotFoundException(String resourceName, String filedName, String filedValue) {
        super(String.format("No %s found with %s: '%s'", resourceName, filedName, filedValue));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.filedValue = filedValue;
    }

    public String getDetails() {
        return String.format("%s with %s: '%s' was not found in the database",
                this.resourceName, this.filedName, this.filedValue);
    }
}