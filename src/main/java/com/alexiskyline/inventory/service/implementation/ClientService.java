package com.alexiskyline.inventory.service.implementation;

import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.entity.Region;
import com.alexiskyline.inventory.repository.IClientRepository;
import com.alexiskyline.inventory.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private IClientRepository clientRepository;

    @Override
    @Transactional
    public Client save(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        return this.clientRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findById(Long id) {
        return this.clientRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.clientRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegions() {
        return this.clientRepository.findAllRegios();
    }
}