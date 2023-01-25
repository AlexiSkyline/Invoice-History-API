package com.alexiskyline.inventory.service;

import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService extends ICrudService<Client> {
    List<Client> findAll();
    Client save(Client client);
    Page<Client> findAll(Pageable pageable);
    List<Region> findAllRegions();
}