package com.example.domains.entities.models;

import com.example.domains.entities.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor 
@Schema(name = "Actor", description = "Datos del actor")
public class ActorDTO {
    @JsonProperty("id")
    private int actorId;
    @Schema(name = "Nombre del Actor", example = "Amaia", required = true, minLength = 2, maxLength = 45)
    @JsonProperty("firstName")
    private String firstName;
    @Schema(name = "Apellido del Actor", example = "Montero", required = true, minLength = 2, maxLength = 45)

    @JsonProperty("lastName")
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