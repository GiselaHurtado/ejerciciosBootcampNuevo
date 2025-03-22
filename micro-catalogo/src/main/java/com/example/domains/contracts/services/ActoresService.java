package com.example.domains.contracts.services;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.domains.core.contracts.services.ProjectionDomainService;
import com.example.domains.entities.Actor;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

public interface ActoresService extends ProjectionDomainService<Actor, Integer> {
    void repartePremios();

    List<Actor> novedades(Date fecha);
}
