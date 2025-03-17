package com.example.domains.entities.models;

import com.example.domains.entities.Actor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor
public class ActorShort {
    int actorId;
    String nombre;
    
    public static ActorShort from(Actor source) {
        return new ActorShort(
                source.getActorId(), 
                source.getFirstName() + " " + source.getLastName()
                );
    }
}