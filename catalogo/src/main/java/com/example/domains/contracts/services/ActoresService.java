package com.example.domains.contracts.services;

import org.springframework.stereotype.Service;

import com.example.domains.core.contracts.services.DomainService;
import com.example.domains.entities.Actor;

@Service
public interface ActoresService extends DomainService<Actor, Integer> {
	void repartePremios();

}
