package com.alexiskyline.inventory.service.implementation;

import com.alexiskyline.inventory.dto.ClientDTO;
import com.alexiskyline.inventory.dto.ClientRegistrationRequest;
import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.entity.Region;
import com.alexiskyline.inventory.repository.IClientRepository;
import com.alexiskyline.inventory.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private final IClientRepository clientRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public ClientDTO register(ClientRegistrationRequest request) {
        Client newClient = this.clientRepository
                .save(new Client(request.name(), request.lastName(), request.email()));
        return this.mapper.map(newClient, ClientDTO.class);
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
    public Client update(Client client) {
        return this.clientRepository.save(client);
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

    @Override
    @Transactional
    public Client setRegion(Long idClient, Region region) {
        Optional<Client> foundClient = this.clientRepository.findById(idClient);
        foundClient.get().setRegion(region);
        return this.clientRepository.save(foundClient.get());
    }
}