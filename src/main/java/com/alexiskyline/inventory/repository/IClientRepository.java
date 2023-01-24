package com.alexiskyline.inventory.repository;

import com.alexiskyline.inventory.entity.Client;
import com.alexiskyline.inventory.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    @Query("from Region")
    List<Region> findAllRegios();
}