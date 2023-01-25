package com.alexiskyline.inventory.dto;

import jakarta.validation.constraints.NotEmpty;

public record InvoiceRegistrationRequest(
        @NotEmpty
        String description,
        @NotEmpty
        String observation
) {}