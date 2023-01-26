package com.alexiskyline.inventory.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClientRequest {
    @NotEmpty
    @Size(min = 4, max = 25)
    private String name;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email
    private String email;
}