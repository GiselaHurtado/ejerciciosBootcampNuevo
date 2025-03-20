package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domains.entities.Actor;

public interface ActoresRepository extends JpaRepository<Actor, Integer>, JpaSpecificationExecutor<Actor> {

	List<Actor> findByActorId(Integer actorID);
	List<Actor> findByFirstName(String firstName);
	List<Actor> findByLastName(String lastName);

	List<Actor> findTop5ByFirstNameStartingWithOrderByLastNameDesc(String prefijo);
    List<Actor> findTop5ByFirstNameStartingWith(String prefijo, Sort orderBy);
    
    List<Actor> findByActorIdGreaterThan(Integer id);
    @Query(value = "SELECT a FROM Actor a WHERE a.actorId > ?1")
    List<Actor> findNovedadesJPQL(Integer id);
    @Query(value = "SELECT * FROM actor a WHERE a.actor_id > :id", nativeQuery = true)
    List<Actor> findNovedadesSQL(@Param("id")Integer  id);
    
    List<Actor> queryByActorIdGreaterThan(Integer id);
    List<Actor> getByActorIdGreaterThan(Integer id);
    
    <T> List<T> findByActorIdGreaterThan(Integer id, Class<T> type);
}