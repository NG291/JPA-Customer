package com.customerjpa.service;

import java.util.List;

public interface GenerateService<T> {
    List<T> findAll();

    T findById(Long id);

    void save(T t);

    void remove(Long id);
}
