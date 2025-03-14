package com.example.domains.contracts.services;

import java.util.List;
import java.util.Optional;

public interface DomainService<T, ID> {
    List<T> getAll();
    Optional<T> getOne(ID id);
    T add(T item) throws Exception;
    T modify(T item) throws Exception;
    void delete(T item) throws Exception;
    void deleteById(ID id) throws Exception;
}
