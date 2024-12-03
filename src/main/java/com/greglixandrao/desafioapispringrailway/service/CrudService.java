package com.greglixandrao.desafioapispringrailway.service;

import java.util.List;

public interface CrudService<ID, T> {
    List<T> findAll();
    T findById(ID id);
    T create(T t);
    T update(ID id, T entity);
    void delete(ID id);
}
