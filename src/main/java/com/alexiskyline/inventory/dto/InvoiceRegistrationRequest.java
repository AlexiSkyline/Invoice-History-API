package com.alexiskyline.inventory.dto;

public record InvoiceRegistrationRequest(
    String description,
    String observation
) {}