package com.alexiskyline.inventory.service.implementation;

import com.alexiskyline.inventory.dto.InvoiceDTO;
import com.alexiskyline.inventory.dto.InvoiceDTOMapper;
import com.alexiskyline.inventory.dto.InvoiceRegistrationRequest;
import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.entity.Invoice;
import com.alexiskyline.inventory.entity.ItemInvoice;
import com.alexiskyline.inventory.exception.ResourceNotFoundException;
import com.alexiskyline.inventory.repository.IInvoiceRepository;
import com.alexiskyline.inventory.service.IInvoiceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class InvoiceService implements IInvoiceService {
    private final IInvoiceRepository invoiceRepository;
    private final InvoiceDTOMapper invoiceDTOMapper;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public Invoice save(InvoiceRegistrationRequest request) {
        Invoice newInvoice = new Invoice(request.description(), request.observation());
        return this.invoiceRepository.save(newInvoice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceDTO> findAll() {
        return StreamSupport.stream(this.invoiceRepository.findAll().spliterator(), false)
                .map(invoiceDTOMapper)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Invoice findById(Long id) {
        return this.getFoundInvoiceById(id);
    }

    @Override
    @Transactional
    public Invoice delete(Long id) {
        Invoice foundInvoice = this.getFoundInvoiceById(id);
        this.invoiceRepository.deleteById(id);
        return foundInvoice;
    }

    @Override
    @Transactional
    public InvoiceDTO setClient(Long id, Client client) {
        Invoice foundInvoice = this.getFoundInvoiceById(id);
        foundInvoice.setClient(client);
        this.invoiceRepository.save(foundInvoice);
        return this.mapper.map(foundInvoice, InvoiceDTO.class);
    }

    @Override
    @Transactional
    public InvoiceDTO addItem(Long id, ItemInvoice itemInvoice) {
        Invoice foundInvoice = this.getFoundInvoiceById(id);
        foundInvoice.getItems().add(itemInvoice);
        this.invoiceRepository.save(foundInvoice);
        return this.mapper.map(foundInvoice, InvoiceDTO.class);
    }

    private Invoice getFoundInvoiceById(Long id) {
        Optional<Invoice> foundInvoice = this.invoiceRepository.findById(id);
        if (foundInvoice.isEmpty()) {
            throw new ResourceNotFoundException("Invoice", "ID", id.toString());
        }
        return foundInvoice.get();
    }
}