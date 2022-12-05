package com.example.bd_project.dao;

import java.util.List;

interface Dao<T, V> {

    List<T> show();
    void add(T entity);
    void delete(V entity);
    void change(T entity, V key);
    void close();
}
