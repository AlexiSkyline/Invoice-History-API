package com.alexiskyline.inventory.repository;

import com.alexiskyline.inventory.entity.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceRepository extends CrudRepository<Invoice, Long> {}