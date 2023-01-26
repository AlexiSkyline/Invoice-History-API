package com.alexiskyline.inventory.controller;

import com.alexiskyline.inventory.dto.ClientDTO;
import com.alexiskyline.inventory.dto.ClientRequest;
import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.payload.response.ApiResponse;
import com.alexiskyline.inventory.service.IClientService;
import com.alexiskyline.inventory.service.IUploadFileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static com.alexiskyline.inventory.payload.response.ResponseHandler.responseBuild;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final IClientService clientService;
    private final IUploadFileService uploadFileService;

    @PostMapping
    public ResponseEntity<ApiResponse<ClientDTO>> registerClient(@Valid @RequestBody ClientRequest request) {
        return responseBuild(OK, "Customer successfully registered", this.clientService.register(request));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Client>>> findAllClients() {
        return responseBuild(OK, "List of all registered customers retrieved successfully", this.clientService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Client>> findClientById(@PathVariable Long id) {
        String message = String.format("Client with ID '%d' retrieved successfully.", id);
        return responseBuild(OK, message, this.clientService.findById(id));
    }

    @GetMapping("page/{page}")
    public Page<Client> findAllClientsPage(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return this.clientService.findAll(pageable);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<ClientDTO>> updateClient(@PathVariable Long id, @Valid @RequestBody ClientRequest request) {
        return responseBuild(OK, "Customer successfully updated", this.clientService.updateInformation(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Client>> deleteClient(@PathVariable Long id) {
        Client foundClient = this.clientService.delete(id);
        this.uploadFileService.delete(foundClient.getPhoto());
        return responseBuild(OK, "Customer successfully deleted", foundClient);
    }

    @PatchMapping("uploads")
    public ResponseEntity<ApiResponse<ClientDTO>> uploadClient(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) throws IOException {
        String fileName = this.uploadFileService.copy(file);
        ClientDTO clientDTO = this.clientService.updatePhoto(id, fileName);
        this.uploadFileService.delete(clientDTO.getPhoto());
        return responseBuild(OK, "Customer successfully Upload", clientDTO);
    }

    @GetMapping("uploads/img/{fileName:.+}")
    public ResponseEntity<Resource> viewPhoto(@PathVariable String fileName) throws MalformedURLException {
        Resource resource = this.uploadFileService.load(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"");
        return new ResponseEntity<>(resource, headers, OK);
    }
}