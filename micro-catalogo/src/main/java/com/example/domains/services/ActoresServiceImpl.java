package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.contracts.services.ActoresService;
import com.example.domains.entities.Actor;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class ActoresServiceImpl implements ActoresService {
    private ActoresRepository dao;
    
    public ActoresServiceImpl(ActoresRepository dao) {
        this.dao = dao;
    }
    
    @Override
    public List<Actor> getAll() {
        return dao.findAll();
    }
    
    @Override
    public Optional<Actor> getOne(Integer id) {
        return dao.findById(id);
    }
    
    @Override
    public Actor add(Actor item) throws DuplicateKeyException, InvalidDataException {
        if(item == null) {
            throw new InvalidDataException("El actor no puede ser nulo");
        }
        if(item.isInvalid()) {
            throw new InvalidDataException(item.getErrorsMessage());
        }
        
        if(item.getActorId() > 0 && dao.existsById(item.getActorId())) {
            throw new DuplicateKeyException("El actor ya existe");
        }
        return dao.save(item);
    }
    
    @Override
    public Actor modify(Actor item) throws NotFoundException, InvalidDataException {
        if(item == null) {
            throw new InvalidDataException("El actor no puede ser nulo");
        }
        if(item.isInvalid()) {
            throw new InvalidDataException(item.getErrorsMessage());
        }
        
        if(!dao.existsById(item.getActorId())) {
            throw new NotFoundException("El actor no existe");
        }
        return dao.save(item);
    }
    
    @Override
    public void delete(Actor item) throws InvalidDataException, NotFoundException {
        if(item == null) {
            throw new InvalidDataException("El actor no puede ser nulo");
        }
        if(!dao.existsById(item.getActorId())) {
            throw new NotFoundException("El actor no existe");
        }
        dao.delete(item);
    }
    
    @Override
    public void deleteById(Integer id) throws NotFoundException {
        if(!dao.existsById(id)) {
            throw new NotFoundException("El actor no existe");
        }
        dao.deleteById(id);
    }
    
    @Override
    public void repartePremios() {
        List<Actor> actores = dao.findAll();
        if (actores.isEmpty()) {
            System.out.println("No hay actores para premiar.");
            return;
        }
        int randomIndex = (int) (Math.random() * actores.size());
        Actor ganador = actores.get(randomIndex);
        System.out.println("🎉 El premio al mejor actor es para: " + ganador.getFirstName() + " " + ganador.getLastName() + " 🎬🏆");
    }
}