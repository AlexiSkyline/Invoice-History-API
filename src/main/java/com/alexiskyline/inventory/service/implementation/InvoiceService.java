package com.alexiskyline.inventory.service.implementation;

import com.alexiskyline.inventory.dto.ClientDTO;
import com.alexiskyline.inventory.dto.InvoiceDTO;
import com.alexiskyline.inventory.dto.InvoiceDTOMapper;
import com.alexiskyline.inventory.dto.InvoiceRegistrationRequest;
import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.entity.Invoice;
import com.alexiskyline.inventory.entity.ItemInvoice;
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
    private final ModelMapper mapper = new ModelMapper();

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
    public Optional<Invoice> findById(Long id) {
        return this.invoiceRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.invoiceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public InvoiceDTO addClient(Long id, Client client) {
        Optional<Invoice> foundInvoice = this.invoiceRepository.findById(id);
        foundInvoice.get().setClient(client);
        this.invoiceRepository.save(foundInvoice.get());
        return this.mapper.map(foundInvoice.get(), InvoiceDTO.class);
    }

    @Override
    @Transactional
    public InvoiceDTO addItem(Long id, ItemInvoice itemInvoice) {
        Optional<Invoice> foundInvoice = this.invoiceRepository.findById(id);
        foundInvoice.get().getItems().add(itemInvoice);
        this.invoiceRepository.save(foundInvoice.get());
        return this.mapper.map(foundInvoice.get(), InvoiceDTO.class);
    }
}