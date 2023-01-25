package com.alexiskyline.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "invoices")
@Getter @Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String description;
    private String observation;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @JsonIgnoreProperties(value = {"invoice", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<ItemInvoice> items;

    public Invoice() {
        items = new ArrayList<>();
    }

    public Invoice(String description, String observation) {
        this.description = description;
        this.observation = observation;
    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }
}