package com.alexiskyline.inventory.service;

public interface ICrudService<T> {
    T findById(Long id);
    T delete(Long id);
}