package com.example.domains.services;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.contracts.services.ActoresService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.models.ActorDTO;
import com.example.domains.event.EmitEntityDeleted;
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
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findAllBy(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findAllBy(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findAllBy(pageable, type);
	}

	@Override
	public Iterable<Actor> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Actor> getAll(Pageable pageable) {
		return dao.findAll(pageable);
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
		if(item == null)
			throw new InvalidDataException("No puede ser nulo");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
//		if(item.getActorId() != 0 && dao.existsById(item.getActorId()))
//			throw new DuplicateKeyException("Ya existe");
//		return dao.save(item);
		return dao.save(item);
	}

	@Override
	public Actor modify(Actor item) throws NotFoundException, InvalidDataException {
		if(item == null)
			throw new InvalidDataException("No puede ser nulo");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
//		if(!dao.existsById(item.getActorId()))
//			throw new NotFoundException();
//		return dao.save(item);
		return dao.save(item);
	}

	@Override
	public void delete(Actor item) throws InvalidDataException {
		if(item == null)
			throw new InvalidDataException("No puede ser nulo");
		dao.delete(item);
	}

	@Override
	@EmitEntityDeleted(entityName = "Actore")
	public void deleteById(Integer id) throws NotFoundException, InvalidDataException {
	    try {
	        dao.deleteById(id);
	    } catch (DataIntegrityViolationException e) {
	        
	        throw new InvalidDataException("No se puede eliminar el actor porque tiene relaciones asociadas", e);
	    }
	}


	@Override
	public void repartePremios() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Actor> novedades(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Actor> novedades(Date fecha) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Actor> novedades(Date fecha) {
//		return dao.findByLastUpdateGreaterThanEqualOrderByLastUpdate(fecha);
//	}
}