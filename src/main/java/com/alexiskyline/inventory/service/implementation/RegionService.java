package com.alexiskyline.inventory.service.implementation;

import com.alexiskyline.inventory.entity.Region;
import com.alexiskyline.inventory.exception.DuplicateResourceException;
import com.alexiskyline.inventory.exception.ResourceNotFoundException;
import com.alexiskyline.inventory.repository.IRegionRepository;
import com.alexiskyline.inventory.service.IRegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionService implements IRegionService {
    private final IRegionRepository regionRepository;

    @Override
    @Transactional
    public Region save(Region region) {
        Optional<Region> foundRegion = this.regionRepository.findRegionByName(region.getName());
        if (foundRegion.isPresent()) {
            throw new DuplicateResourceException("Region", "Name", region.getName());
        }
        return this.regionRepository.save(region);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegion() {
        return this.regionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Region findById(Long id) {
        return this.regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region", "ID", id.toString()));
    }

    @Override
    @Transactional
    public Region delete(Long id) {
        Optional<Region> foundRegion = this.regionRepository.findById(id);
        if (foundRegion.isEmpty()) {
            throw new ResourceNotFoundException("Region", "ID", id.toString());
        }
        this.regionRepository.deleteById(id);
        return foundRegion.get();
    }
}