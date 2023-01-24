package com.alexiskyline.inventory.service;

import java.util.List;

public interface ICrudService<T> {
    T save(T t);
    List<T> findAll();
    T findById(Long id);
    void delete(Long id);
}