package com.solvd.dao;

public interface IBaseDAO <T> {
    T getEntityById(long id);
    void saveEntity(T entity);
    void updateEntity(long id, T entity);
    void deleteEntityById(long id);
}