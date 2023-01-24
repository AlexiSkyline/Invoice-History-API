package com.alexiskyline.inventory.service.implementation;

import com.alexiskyline.inventory.entity.Invoice;
import com.alexiskyline.inventory.repository.IInvoiceRepository;
import com.alexiskyline.inventory.service.IInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService implements IInvoiceService {
    private final IInvoiceRepository invoiceRepository;

    @Override
    @Transactional
    public Invoice save(Invoice invoice) {
        return this.invoiceRepository.save(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Invoice> findAll() {
        return (List<Invoice>) this.invoiceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Invoice findById(Long id) {
        return this.invoiceRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.invoiceRepository.deleteById(id);
    }
}