package com.alexiskyline.inventory.controller;

import com.alexiskyline.inventory.entity.Invoice;
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
    public ResponseEntity<Invoice> saveInvoice(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(this.invoiceService.save(invoice));
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> findAllInvoices() {
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
}