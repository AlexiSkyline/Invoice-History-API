package com.alexiskyline.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "items_invoice")
@Getter @Setter
public class ItemInvoice {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Integer cantidad;
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}