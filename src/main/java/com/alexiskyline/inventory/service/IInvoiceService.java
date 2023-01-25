package com.alexiskyline.inventory.service;

import com.alexiskyline.inventory.dto.InvoiceDTO;
import com.alexiskyline.inventory.dto.InvoiceRegistrationRequest;
import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.entity.Invoice;
import com.alexiskyline.inventory.entity.ItemInvoice;

import java.util.List;

public interface IInvoiceService extends ICrudService<Invoice> {
    Invoice save(InvoiceRegistrationRequest request);
    List<InvoiceDTO> findAll();
    InvoiceDTO addClient(Long id, Client client);
    InvoiceDTO addItem(Long id, ItemInvoice itemInvoice);
}