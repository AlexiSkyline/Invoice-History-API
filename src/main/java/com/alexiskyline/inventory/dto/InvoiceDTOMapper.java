package com.alexiskyline.inventory.dto;

import com.alexiskyline.inventory.entity.Invoice;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InvoiceDTOMapper implements Function<Invoice, InvoiceDTO> {
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public InvoiceDTO apply(Invoice invoice) {
        return new InvoiceDTO(
                invoice.getId(),
                invoice.getDescription(),
                invoice.getObservation(),
                invoice.getCreateAt(),
                this.mapper.map(invoice.getClient(), ClientDTO.class),
                invoice.getItems()
        );
    }
}