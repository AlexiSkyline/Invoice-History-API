package com.alexiskyline.inventory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "regions")
@Getter @Setter
public class Region {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
}