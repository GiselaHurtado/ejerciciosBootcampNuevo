package com.example.domains.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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
        return dao.findAll().stream().map(type::cast).toList();
    }

    @Override
    public <T> List<T> getByProjection(Sort sort, Class<T> type) {
        return dao.findAll(sort).stream().map(type::cast).toList();
    }

    @Override
    public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
        return dao.findAll(pageable).map(type::cast);
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
        if (item == null)
            throw new InvalidDataException("No puede ser nulo");
        if (item.isInvalid())
            throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
        return dao.save(item);
    }

    @Override
    public Actor modify(Actor item) throws NotFoundException, InvalidDataException {
        if (item == null)
            throw new InvalidDataException("No puede ser nulo");
        if (item.isInvalid())
            throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
        return dao.save(item);
    }

    @Override
    public void delete(Actor item) throws InvalidDataException {
        if (item == null)
            throw new InvalidDataException("No puede ser nulo");
        dao.delete(item);
    }

    @Override
    @EmitEntityDeleted(entityName = "Actore")
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public void repartePremios() {
    }

    @Override
    public List<Actor> novedades(Timestamp fecha) {
        return dao.findByLastUpdateGreaterThanEqualOrderByLastUpdate(fecha);
    }
}