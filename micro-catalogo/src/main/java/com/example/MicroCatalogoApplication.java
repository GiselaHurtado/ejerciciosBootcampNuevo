package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.contracts.services.ActoresService;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.ActorShort;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class MicroCatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MicroCatalogoApplication.class, args);
	}
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
		ejemplosDatos();
	}
	
	@Autowired
	private ActoresRepository dao;
	
	@Autowired
	private ActoresService srv;
	
	private void ejemplosDatos() {
		//var actor = new Actor(0, null, "IAN MIKU");
		//if(actor.isValid());
		//dao.save(actor);
		//else {
		dao.findByActorIdGreaterThan(200).forEach(System.err::println);
		dao.findByActorIdGreaterThan(200, ActorDTO.class).forEach(System.err::println);
		dao.findByActorIdGreaterThan(200, ActorShort.class).forEach(o -> System.err.println(o.getId() + " " + o.getNombre()));
	}
		//System.err.println(actor.getErrorsMessage());

	public ActoresService getSrv() {
		return srv;
	}

	public void setSrv(ActoresService srv) {
		this.srv = srv;
	}
	}
	

