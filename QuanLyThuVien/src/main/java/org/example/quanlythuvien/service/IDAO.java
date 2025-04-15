package org.example.quanlythuvien.service;

import java.util.List;

public interface IDAO<T> {
    List<T> getAll();
    T getById(String id);
    void add(T entity);
    void update(T entity);
    void delete(String id);
}