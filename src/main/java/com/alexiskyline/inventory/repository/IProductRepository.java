package com.alexiskyline.inventory.repository;

import com.alexiskyline.inventory.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends CrudRepository<Product, Long> {
    @Query("select p from Product p where p.name like %?1%")
    List<Product> findByName(String term);
    List<Product> findByNameContainingIgnoreCase(String term);
    List<Product> findByNameStartingWithIgnoreCase(String term);
}