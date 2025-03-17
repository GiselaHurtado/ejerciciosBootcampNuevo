package com.example.domains.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FilmActorPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "actor_id", insertable = false, updatable = false, nullable = false)
    private int actorId;

    @Column(name = "film_id", insertable = false, updatable = false, nullable = false)
    private int filmId;

    public FilmActorPK() {
    }

    public FilmActorPK(int actorId, int filmId) {
        this.actorId = actorId;
        this.filmId = filmId;
    }

    public int getActorId() {
        return this.actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public int getFilmId() {
        return this.filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmActorPK that = (FilmActorPK) o;
        return actorId == that.actorId && filmId == that.filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, filmId);
    }
}