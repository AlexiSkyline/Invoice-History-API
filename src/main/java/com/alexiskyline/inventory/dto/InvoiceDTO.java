package com.alexiskyline.inventory.dto;

import com.alexiskyline.inventory.entity.ItemInvoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class InvoiceDTO {
    private Long id;
    private String description;
    private String observation;
    private Date createAt;
    private ClientDTO client;
    private List<ItemInvoice> items;
}