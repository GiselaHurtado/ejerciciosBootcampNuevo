package com.example.domains.contracts.services;

import java.util.List;
import java.util.Optional;

import com.example.domains.entities.Actor;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

public interface ActoresService {
    List<Actor> getAll();
    
    Optional<Actor> getOne(Integer id);
    
    Actor add(Actor item) throws DuplicateKeyException, InvalidDataException;
    
    Actor modify(Actor item) throws NotFoundException, InvalidDataException;
   
    void delete(Actor item) throws InvalidDataException;
   
    void deleteById(Integer id) throws InvalidDataException;
    
    void repartePremios();
}
