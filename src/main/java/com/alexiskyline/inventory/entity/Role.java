package com.alexiskyline.inventory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "roles")
@Getter @Setter
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(unique = true, length = 20)
    private String name;
}