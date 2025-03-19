package ru.dima.NaumenJava.dao;

import java.util.List;

public interface CrudRepository <T, ID>{
    void create(T task);
    T read(ID id);
    void update(T updatedTask);
    void delete(ID id);
    List<T> readAll();
}