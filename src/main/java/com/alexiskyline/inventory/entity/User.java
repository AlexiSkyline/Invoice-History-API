package com.alexiskyline.inventory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @Column(unique = true, length = 20)
    private String userName;
    @Column(unique = true)
    private String email;
    @Column(length = 60)
    private String password;
    private Boolean enabled;
}