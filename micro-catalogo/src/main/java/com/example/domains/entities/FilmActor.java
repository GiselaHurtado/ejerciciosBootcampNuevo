package com.example.domains.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "film_actor")
public class FilmActor implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FilmActorPK id = new FilmActorPK();

    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    private Film film;

    public FilmActor() {
        this.id = new FilmActorPK();
    }

    public FilmActor(Film film2, Actor actor2) {
		// TODO Auto-generated constructor stub
	}

	public FilmActorPK getId() { return id; }
    public void setId(FilmActorPK id) { this.id = id; }

    public Timestamp getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(Timestamp lastUpdate) { this.lastUpdate = lastUpdate; }

    public Actor getActor() { return actor; }
    public void setActor(Actor actor) { 
    	this.actor = actor;
    	this.id.setActorId(actor.getActorId());}
    
    

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film;
    this.id.setFilmId(film.getFilmId()); }

	public Object prePersiste() {
		// TODO Auto-generated method stub
		return null;
	}
}
