package com.example.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.example.domains.entities.Actor;
import com.example.domains.entities.models.ActorDTO;
import com.example.domains.entities.models.ActorShort;
import com.example.test.utils.Lentos;

@SpringBootTest
@Lentos
@Transactional
class ActorRepositoryRealTest {
    @Autowired
    ActoresRepository dao;
    
    @Test
    void testFindAll() {
        assertThat(dao.findAll().size()).isGreaterThan(0);
    }
    
    @Test
    void testFindById() {
        var item = dao.findById(1);
        assertThat(item.isPresent()).isTrue();
    }
    
    @Test
    void testFindTop5ByFirstNameStartingWithOrderByLastNameDesc() {
        var rslt = dao.findTop5ByFirstNameStartingWithOrderByLastNameDesc("P");
        assertThat(rslt.size()).isLessThanOrEqualTo(5);
        if (rslt.size() > 1) {
            assertThat(rslt.get(0).getLastName().compareTo(rslt.get(1).getLastName())).isGreaterThanOrEqualTo(0);
        }
    }
    
    @Test
    void testFindTop5ByFirstNameStartingWith() {
        var rslt = dao.findTop5ByFirstNameStartingWith("P", Sort.by("firstName"));
        assertThat(rslt.size()).isLessThanOrEqualTo(5);
        if (rslt.size() > 1) {
            assertThat(rslt.get(0).getFirstName().compareTo(rslt.get(1).getFirstName())).isLessThanOrEqualTo(0);
        }
    }
    
    @Test
    void testFindByActorIdGreaterThan() {
        var rslt = dao.findByActorIdGreaterThan(200);
        assertThat(rslt.size()).isGreaterThan(0);
        for (Actor actor : rslt) {
            assertThat(actor.getActorId()).isGreaterThan(200);
        }
    }
    
    @Test
    void testFindNovedadesJPQL() {
        var rslt = dao.findNovedadesJPQL(200);
        assertThat(rslt.size()).isGreaterThan(0);
    }
    
    @Test
    void testFindNovedadesSQL() {
        var rslt = dao.findNovedadesSQL(200);
        assertThat(rslt.size()).isGreaterThan(0);
    }
    
    @Test
    void testQueryByActorIdGreaterThan() {
        var rslt = dao.queryByActorIdGreaterThan(200);
        assertThat(rslt.size()).isGreaterThan(0);
    }
    
    @Test
    void testGetByActorIdGreaterThan() {
        var rslt = dao.getByActorIdGreaterThan(200);
        assertThat(rslt.size()).isGreaterThan(0);
    }
    
    @Test
    void testFindByActorIdGreaterThanWithType() {
        
        var dtos = dao.findByActorIdGreaterThan(200, ActorDTO.class);
        assertThat(dtos.size()).isGreaterThan(0);
        
       
        var shorts = dao.findByActorIdGreaterThan(200, ActorShort.class);
        assertThat(shorts.size()).isGreaterThan(0);
    }
}