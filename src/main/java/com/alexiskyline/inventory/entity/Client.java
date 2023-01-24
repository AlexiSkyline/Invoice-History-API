package com.alexiskyline.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "clients")
@Getter @Setter
public class Client {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    private String photo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Region region;
}