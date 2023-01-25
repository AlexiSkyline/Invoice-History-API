package com.alexiskyline.inventory.controller;

import com.alexiskyline.inventory.dto.InvoiceDTO;
import com.alexiskyline.inventory.dto.InvoiceRegistrationRequest;
import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.entity.Invoice;
import com.alexiskyline.inventory.entity.ItemInvoice;
import com.alexiskyline.inventory.service.IInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final IInvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Invoice> saveInvoice(@RequestBody InvoiceRegistrationRequest request) {
        return ResponseEntity.ok(this.invoiceService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> findAllInvoices() {
        return ResponseEntity.ok(this.invoiceService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Invoice> findInvoicesByID(@PathVariable Long id) {
        Optional<Invoice> foundInvoice = this.invoiceService.findById(id);
        return foundInvoice.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable Long id) {
        Optional<Invoice> foundInvoice = this.invoiceService.findById(id);
        if (foundInvoice.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        this.invoiceService.delete(id);
        return ResponseEntity.ok(foundInvoice.get());
    }

    @PostMapping("{id}/client")
    public ResponseEntity<InvoiceDTO> addClientByInvoiceID(@PathVariable Long id, @RequestBody Client client) {
        return ResponseEntity.ok(this.invoiceService.addClient(id, client));
    }

    @PostMapping("{id}/item")
    public ResponseEntity<InvoiceDTO> addItemByInvoiceID(@PathVariable Long id, @RequestBody ItemInvoice itemInvoice) {
        return ResponseEntity.ok(this.invoiceService.addItem(id, itemInvoice));
    }
}