package org.clevertec.checkcrud.service;


public interface CrudService<T, ID> {

    T getById(ID id);
    T save(T t);
    T update(T t);
    boolean deleteById(ID id);
}
