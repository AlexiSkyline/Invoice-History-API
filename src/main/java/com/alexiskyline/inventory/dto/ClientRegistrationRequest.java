package com.alexiskyline.inventory.dto;

public record ClientRegistrationRequest(
        String name,
        String lastName,
        String email
) {}