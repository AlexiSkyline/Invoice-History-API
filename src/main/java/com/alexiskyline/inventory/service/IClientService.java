package com.alexiskyline.inventory.service;

import com.alexiskyline.inventory.dto.ClientDTO;
import com.alexiskyline.inventory.dto.ClientRegistrationRequest;
import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService extends ICrudService<Client> {
    List<Client> findAll();
    ClientDTO register(ClientRegistrationRequest request);
    Client update(Client client);
    Page<Client> findAll(Pageable pageable);
    List<Region> findAllRegions();
    Client setRegion(Long idClient, Region region);
}