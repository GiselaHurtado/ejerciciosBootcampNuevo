package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.entities.Actor;

@SpringBootApplication
public class MicroCatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MicroCatalogoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
		ejemplosDatos();
	}
	
	@Autowired
	private ActoresRepository dao;
	
	private void ejemplosDatos() {
		var actor = new Actor(0, null, "IAN MIKU");
		//if(actor.isValid());
		dao.save(actor);
		//else {
		System.err.println(actor.getErrorsMessage());
	}
	}

