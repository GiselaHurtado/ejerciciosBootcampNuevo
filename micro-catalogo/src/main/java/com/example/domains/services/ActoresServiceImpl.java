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
    private final ActoresRepository dao;

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
    public Actor modify(Actor item) throws NotFoundException, InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("El actor no puede ser nulo.");
        }
        if (item.isInvalid()) {  
            throw new InvalidDataException("El ID del actor es inválido.");
        }
        if (!dao.existsById(item.getActorId())) {
            throw new NotFoundException("Actor no encontrado.");
        }
        return dao.save(item);
    }


   
    @Override
    public void delete(Actor item) throws InvalidDataException {
        if (item == null || item.getActorId() <= 0) {
            throw new InvalidDataException("Actor no válido.");
        }
        if (!dao.existsById(item.getActorId())) {
            throw new InvalidDataException("El actor no existe en la base de datos.");
        }
        dao.delete(item);
    }

    @Override
    public void repartePremios() {
        List<Actor> actores = dao.findAll();
        if (actores.isEmpty()) {
            System.out.println("No hay actores para premiar.");
            return;
        }

        Actor ganador = actores.get((int) (Math.random() * actores.size()));
        System.out.println("🎉 El premio al mejor actor es para: " + ganador.getFirstName() + " " + ganador.getLastName() + " 🎬🏆");
    }

	@Override
	public Actor add(Actor item) throws DuplicateKeyException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) throws InvalidDataException {
		// TODO Auto-generated method stub
		
	}
}
