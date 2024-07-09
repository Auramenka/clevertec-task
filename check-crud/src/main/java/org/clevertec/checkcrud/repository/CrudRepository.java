package org.clevertec.checkcrud.repository;


public interface CrudRepository<T, ID> {

    T getById(ID id);
    T save(T t);
    T update(T t);
    boolean deleteById(ID id);
}
