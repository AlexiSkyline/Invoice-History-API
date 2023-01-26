package com.alexiskyline.inventory.repository;

import com.alexiskyline.inventory.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findRegionByName(String name);
}