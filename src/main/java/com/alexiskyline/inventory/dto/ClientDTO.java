package com.alexiskyline.inventory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClientDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String createAt;
    private String photo;
}