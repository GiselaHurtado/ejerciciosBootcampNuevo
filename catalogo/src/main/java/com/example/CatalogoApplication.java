package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.entities.Actor;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
		ejemplosDatos();
	}
	
	@Autowired
	private ActoresRepository dao;
	
	private void ejemplosDatos() {
		var actor = new Actor(0, "Peoito", "Grillo");
		//var item = dao.findById(204);
		dao.save(actor);
		dao.findAll().forEach(System.err::println);
	}

}
