package com.alexiskyline.inventory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products")
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private Double price;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }
}