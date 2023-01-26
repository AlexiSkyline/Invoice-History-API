package com.alexiskyline.inventory.controller;

import com.alexiskyline.inventory.dto.InvoiceDTO;
import com.alexiskyline.inventory.dto.InvoiceRegistrationRequest;
import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.entity.Invoice;
import com.alexiskyline.inventory.entity.ItemInvoice;
import com.alexiskyline.inventory.payload.response.ApiResponse;
import com.alexiskyline.inventory.service.IInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alexiskyline.inventory.payload.response.ResponseHandler.responseBuild;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final IInvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<ApiResponse<Invoice>> saveInvoice(@Valid @RequestBody InvoiceRegistrationRequest request) {
        return responseBuild(OK, "Invoice successfully registered", this.invoiceService.save(request));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<InvoiceDTO>>> findAllInvoices() {
        return responseBuild(OK, "List of all registered Invoices retrieved successfully", this.invoiceService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Invoice>> findInvoicesByID(@PathVariable Long id) {
        String message = String.format("Invoice with ID '%d' retrieved successfully.", id);
        return responseBuild(OK, message, this.invoiceService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Invoice>> deleteInvoice(@PathVariable Long id) {
        return responseBuild(OK, "Invoice successfully deleted", this.invoiceService.delete(id));
    }

    @PatchMapping("{id}/client")
    public ResponseEntity<ApiResponse<InvoiceDTO>> addClientByInvoiceID(@PathVariable Long id, @RequestBody Client client) {
        return responseBuild(OK, "Client successfully added to Invoice", this.invoiceService.setClient(id, client));
    }

    @PatchMapping("{id}/item")
    public ResponseEntity<ApiResponse<InvoiceDTO>> addItemByInvoiceID(@PathVariable Long id, @RequestBody ItemInvoice itemInvoice) {
        return responseBuild(OK, "Item successfully added to Invoice", this.invoiceService.addItem(id, itemInvoice));
    }
}