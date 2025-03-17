package com.example.domains.entities.models;


import org.springframework.beans.factory.annotation.Value;

public interface ActorShort {
	@Value("#{target.actorId}")
	int getId();
	@Value("#{target.lastName + ', ' + target.firstName}")
	String getNombre();
}