package com.example.taskapp.services;

import java.util.List;

public interface IController<T>{

    void save(T entity);

    void delete(T entity);

    List<T>getAll();

    List<T> getAllByAsec();

    List<T> getAllByDesc();

    List<T> getAllByAsecName();

    List<T> getAllByDescName();

    T getById(Long id);

    void edit(T entity);

}