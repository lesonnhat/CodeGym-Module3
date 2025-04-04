package com.codegym.c1224g1demoservlet.service;

import com.codegym.c1224g1demoservlet.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<E> {
    List<E> findAll();
    List<E> findAllWithStoreProcedure();
    void save(E entity) throws SQLException;
    void saveWithStoreProcedure(E entity) throws SQLException;
    public E findById(int id);
    public E findByIdWithStoreProcedure(int id);
    public boolean update(E entity) throws SQLException;
    public boolean updateWithStoreProcedure(E entity) throws SQLException;
}
