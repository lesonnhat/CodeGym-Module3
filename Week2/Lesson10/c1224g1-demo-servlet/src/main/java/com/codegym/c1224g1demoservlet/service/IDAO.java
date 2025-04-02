package com.codegym.c1224g1demoservlet.service;

import java.util.List;

public interface IDAO<E> {
    List<E> findAll();
}
