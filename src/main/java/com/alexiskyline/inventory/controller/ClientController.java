package com.alexiskyline.inventory.controller;

import com.alexiskyline.inventory.dto.ClientDTO;
import com.alexiskyline.inventory.dto.ClientRegistrationRequest;
import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.entity.Region;
import com.alexiskyline.inventory.service.IClientService;
import com.alexiskyline.inventory.service.IUploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final IClientService clientService;
    private final IUploadFileService uploadFileService;

    @PostMapping
    public ResponseEntity<ClientDTO> registerClient(@RequestBody ClientRegistrationRequest request) {
        return ResponseEntity.ok(this.clientService.register(request));
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAllClients() {
        return ResponseEntity.ok(this.clientService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> findClientById(@PathVariable Long id) {
        return this.clientService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("page/{page}")
    public Page<Client> findAllClientsPage(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return this.clientService.findAll(pageable);
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        Optional<Client> foundClient = this.clientService.findById(id);
        if (foundClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        foundClient.get().setName(client.getName());
        foundClient.get().setLastName(client.getLastName());
        foundClient.get().setEmail(client.getEmail());
        foundClient.get().setRegion(client.getRegion());

        return ResponseEntity.ok(this.clientService.update(foundClient.get()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
        Optional<Client> foundClient = this.clientService.findById(id);
        if (foundClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        this.uploadFileService.delete(foundClient.get().getPhoto());
        this.clientService.delete(id);
        return ResponseEntity.ok(foundClient.get());
    }

    @PostMapping("uploads")
    public ResponseEntity<Client> uploadClient(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) throws IOException {
        Optional<Client> foundClient = this.clientService.findById(id);
        if (foundClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String fileName = this.uploadFileService.copy(file);
        this.uploadFileService.delete(foundClient.get().getPhoto());
        foundClient.get().setPhoto(fileName);
        return ResponseEntity.ok(this.clientService.update(foundClient.get()));
    }

    @GetMapping("uploads/img/{fileName:.+}")
    public ResponseEntity<Resource> viewPhoto(@PathVariable String fileName) throws MalformedURLException {
        Resource resource = this.uploadFileService.load(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping("{id}/region")
    public ResponseEntity<Client> setRegionByClientId(@PathVariable Long id, @RequestBody Region region) {
        return ResponseEntity.ok(this.clientService.setRegion(id, region));
    }
}