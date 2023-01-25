package com.alexiskyline.inventory.service.implementation;

import com.alexiskyline.inventory.dto.ClientDTO;
import com.alexiskyline.inventory.dto.ClientRequest;
import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.entity.Region;
import com.alexiskyline.inventory.exception.ResourceNotFoundException;
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
    public ClientDTO register(ClientRequest request) {
        Client newClient = this.clientRepository
                .save(new Client(request.getName(), request.getLastName(), request.getEmail()));
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
    public Client findById(Long id) {
        return this.getFoundByClientId(id);
    }

    @Override
    @Transactional
    public ClientDTO updateInformation(Long id, ClientRequest request) {
        Client foundClient = this.getFoundByClientId(id);
        foundClient.setName(request.getName());
        foundClient.setLastName(request.getLastName());
        foundClient.setEmail(request.getEmail());
        return this.mapper.map(this.clientRepository.save(foundClient), ClientDTO.class);
    }

    @Override
    @Transactional
    public ClientDTO updatePhoto(Long id, String fileName) {
        Client foundClient = this.getFoundByClientId(id);
        foundClient.setPhoto(fileName);
        return this.mapper.map(this.clientRepository.save(foundClient), ClientDTO.class);
    }

    @Override
    @Transactional
    public Client delete(Long id) {
        Client foundClient = this.getFoundByClientId(id);
        this.clientRepository.deleteById(id);
        return foundClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegions() {
        return this.clientRepository.findAllRegios();
    }

    @Override
    @Transactional
    public Client setRegion(Long idClient, Region region) {
        Client foundClient = this.getFoundByClientId(idClient);
        foundClient.setRegion(region);
        return this.clientRepository.save(foundClient);
    }

    private Client getFoundByClientId(Long id) {
        Optional<Client> foundClient = this.clientRepository.findById(id);
        if (foundClient.isEmpty()) {
            throw new ResourceNotFoundException("Client", "ID", id.toString());
        }
        return foundClient.get();
    }
}