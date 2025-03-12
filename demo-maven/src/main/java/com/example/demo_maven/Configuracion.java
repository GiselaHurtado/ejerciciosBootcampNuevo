package com.example.demo_maven;

import org.springframework.stereotype.Component;

@Component
public class Configuracion {
	public Configuracion() {
		System.err.println("Configuracion creada");
	}
	public void init() {
		System.err.println("Configuracion inicializada");
	}

}