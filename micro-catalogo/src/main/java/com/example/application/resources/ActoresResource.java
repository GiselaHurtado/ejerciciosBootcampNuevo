package com.example.application.resources;



import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.ActoresService;
import com.example.domains.entities.models.*;
import com.example.domains.entities.models.ActorDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/actores/v1")
public class ActoresResource {
	private ActoresService srv;

	public ActoresResource(ActoresService srv) {
		super();
		this.srv = srv;
	}

	@GetMapping
	public Page<ActorDTO> getAll(Pageable pageable) {
		return srv.getByProjection(pageable, ActorDTO.class);
	}

	//record Titulos(int id, String titulo){}
	
	
	
	@GetMapping(path = "/{id}/pelis")

	public ActorDTO getPeliculas(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty()) {
			throw new NotFoundException("No se encontró el actor con id " + id);
		}
		return ActorDTO.from(item.get());
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ActorDTO item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(ActorDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getActorId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody ActorDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
		if (item.getActorId() != id) {
			throw new BadRequestException("El id del actor no coincide con el recurso a modificar");
		}
		srv.modify(ActorDTO.from(item));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws NotFoundException {
		srv.deleteById(id);
	}
}