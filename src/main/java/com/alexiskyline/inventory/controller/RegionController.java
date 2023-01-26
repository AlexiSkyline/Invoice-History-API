package com.alexiskyline.inventory.controller;

import com.alexiskyline.inventory.entity.Region;
import com.alexiskyline.inventory.payload.response.ApiResponse;
import com.alexiskyline.inventory.service.implementation.RegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alexiskyline.inventory.payload.response.ResponseHandler.responseBuild;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @PostMapping
    public ResponseEntity<ApiResponse<Region>> addRegion(@Valid @RequestBody Region region) {
        return responseBuild(OK, "Region successfully registered", this.regionService.save(region));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Region>>> findAllRegions() {
        return responseBuild(OK, "List of all registered regions retrieved successfully", this.regionService.findAllRegion());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Region>> findRegionById(@PathVariable Long id) {
        String message = String.format("Region with ID '%d' retrieved successfully.", id);
        return responseBuild(OK, message, this.regionService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Region>> deleteRegion(@PathVariable Long id) {
        return responseBuild(OK, "Region successfully deleted", this.regionService.delete(id));
    }
}