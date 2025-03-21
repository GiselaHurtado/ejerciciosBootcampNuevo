package com.example.domains.entities.models;

import com.example.domains.entities.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(name = "Actor", description = "Datos del actor")
public class ActorDTO {
	@JsonProperty("id")
	private Integer actorId;
	
	@JsonProperty("nombre")
	@NotBlank
	@Size(min = 2, max = 45)
	@Schema(description = "Nombre del actor", example = "Amaia", required = true, minLength = 2, maxLength = 45)
	private String firstName;
	
	@JsonProperty("apellidos")
	@NotBlank
	@Size(min = 2, max = 45)
	@Schema(description = "Apellidos del actor", example = "Elaiana")
	private String lastName;

	public static ActorDTO from(Actor source) {
		return new ActorDTO(
				source.getActorId(), 
				source.getFirstName(), 
				source.getLastName()
				);
	}
	public static Actor from(ActorDTO source) {
		return new Actor(
				source.getActorId(), 
				source.getFirstName(), 
				source.getLastName()
				);
	}
}