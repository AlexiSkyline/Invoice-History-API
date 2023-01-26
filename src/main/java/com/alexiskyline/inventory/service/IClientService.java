package com.alexiskyline.inventory.service;

import com.alexiskyline.inventory.dto.ClientDTO;
import com.alexiskyline.inventory.dto.ClientRequest;
import com.alexiskyline.inventory.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService extends ICrudService<Client> {
    List<Client> findAll();
    ClientDTO register(ClientRequest request);
    ClientDTO updateInformation(Long id, ClientRequest client);
    ClientDTO updatePhoto(Long id, String fileName);
    Page<Client> findAll(Pageable pageable);
}