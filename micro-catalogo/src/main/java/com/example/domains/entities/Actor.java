package com.example.domains.entities;

import java.io.Serializable;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.example.domains.core.entities.EntityBase;
import com.example.domains.entities.models.ActorDTO;
import com.example.domains.entities.models.ActorShort;

@Entity
@Table(name = "actor")
@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a")
public class Actor implements Serializable, EntityBase<Actor> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id", unique=true, nullable=false)
    private Integer actorId;
    
    
    @Column(name = "first_name", nullable = false, length = 45)
    @NotBlank
    @Size(max = 45, min = 2)
    @Pattern(regexp = "^[A-Z]+( [A-Z]+)*$", message = "El nombre debe estar en mayúsculas y separado por espacios")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    @NotBlank
    @Size(max = 45, min = 2)
    @Pattern(regexp = "^[A-Z]+( [A-Z]+)*$", message = "El apellido debe estar en mayúsculas y separado por espacios")
    private String lastName;

    @Column(name = "last_update", insertable = false, updatable = false, nullable = false)
    @PastOrPresent
    private Date lastUpdate;
    
    @Column(name = "retired", nullable = false)
    private Boolean retired = false;

    @OneToMany(mappedBy="actor", fetch = FetchType.LAZY)
	private List<FilmActor> filmActors;

	public Actor() {
	}

    public Actor(Integer actorId) {
        this.actorId = actorId;
    }

    public Actor(Integer actorId, String firstName, String lastName) {
        this.actorId = actorId;
        this.firstName = firstName.trim().toUpperCase();
        this.lastName = lastName.trim().toUpperCase();
    }
    
    

    public Integer getActorId() {
        return this.actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        
        System.out.println("En Actor.setFirstName, valor recibido: '" + firstName + "'");
        
        
        if(firstName != null) {
            firstName = firstName.trim();
        }
        
      
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<FilmActor> getFilmActors() {
        return this.filmActors;
    }

    public void setFilmActors(List<FilmActor> filmActors) {
        this.filmActors = filmActors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Actor other = (Actor) obj;
        return actorId == other.actorId;
    }

    @Override
    public String toString() {
        return "Actor [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
    
    public ActorDTO toDTO() {
        return new ActorDTO(actorId, firstName, lastName);
    }

	public boolean isInvalid() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getErrorsMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> getErrorsFields() {
		// TODO Auto-generated method stub
		return null;
	}
}