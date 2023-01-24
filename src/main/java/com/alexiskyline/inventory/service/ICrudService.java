package com.alexiskyline.inventory.service;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T> {
    T save(T t);
    List<T> findAll();
    Optional<T> findById(Long id);
    void delete(Long id);
}