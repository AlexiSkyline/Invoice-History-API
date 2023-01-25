package com.alexiskyline.inventory.service;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T> {
    Optional<T> findById(Long id);
    void delete(Long id);
}