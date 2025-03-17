package com.example.domains.entities;
import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.example.domains.core.entities.AbstractEntity;

@Entity
@Table(name = "actor")
@NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a")
public class Actor extends AbstractEntity<Actor> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actor_id", unique = true, nullable = false)
	private int actorId;
	
	@Column(name = "first_name", nullable = false, length = 45)
	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(max = 45, min = 2, message = "El nombre debe tener entre 2 y 45 caracteres")
	@Pattern(regexp = "^[A-ZÁÉÍÓÚÑ]+( [A-ZÁÉÍÓÚÑ]+)*$", message = "El nombre debe estar en mayúsculas y no contener números")
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 45)
	@NotBlank(message = "El apellido no puede estar vacío")
	@Size(max = 45, min = 2, message = "El apellido debe tener entre 2 y 45 caracteres")
	@Pattern(regexp = "^[A-ZÁÉÍÓÚÑ]+( [A-ZÁÉÍÓÚÑ]+)*$", message = "El apellido debe estar en mayúsculas y no contener números")
	private String lastName;
	
	@Column(name = "last_update", insertable = false, updatable = false, nullable = false)
	@PastOrPresent(message = "La fecha de última actualización debe ser en el pasado o presente")
	@Null(message = "La fecha de actualización se asigna automáticamente")
	private Timestamp lastUpdate;
	
	@OneToMany(mappedBy = "actor")
	@NotNull(message = "La lista de películas no puede ser nula")
	private List<FilmActor> filmActors;
	
	@Column(name = "retired", nullable = false)
	private boolean retired = false;
	
	public Actor() {
	}
	
	public Actor(int actorId, String firstName, String lastName) {
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.filmActors = new ArrayList<>();
		this.retired = false; 
	}
	
	public int getActorId() {
		return actorId;
	}
	
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public List<FilmActor> getFilmActors() {
		return filmActors;
	}
	
	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}
	
	public boolean isRetired() {
		return retired;
	}
	
	public void setRetired(boolean retired) {
		this.retired = retired;
	}
	
	/**
	 * Utility method to check if name is valid according to the pattern.
	 * This method is no longer used for validation but can be useful for other purposes.
	 */
	public boolean hasValidName() {
		return firstName != null && lastName != null && 
			   firstName.matches("^[A-ZÁÉÍÓÚÑ]+( [A-ZÁÉÍÓÚÑ]+)*$") &&
			   lastName.matches("^[A-ZÁÉÍÓÚÑ]+( [A-ZÁÉÍÓÚÑ]+)*$");
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(actorId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Actor other = (Actor) obj;
		return actorId == other.actorId;
	}
	
	@Override
	public String toString() {
		return "Actor [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate=" + lastUpdate + "]";
	}
}