package com.alexiskyline.inventory.service;

import com.alexiskyline.inventory.entity.Region;

import java.util.List;

public interface IRegionService {
    Region save(Region region);
    List<Region> findAllRegion();
    Region findById(Long id);
    Region delete(Long id);
}