package com.example.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import com.example.domains.entities.Actor;
import com.example.test.utils.Lentos;

@DataJpaTest
@ActiveProfiles("test")
@Lentos
class ActorRepositoryMemoryTest {
    @Autowired
    private TestEntityManager em;
    
    @Autowired
    ActoresRepository dao;
    
    @BeforeEach
    void setUp() throws Exception {
        em.getEntityManager().createQuery("DELETE FROM Actor").executeUpdate();
        
        var item = new Actor(0, "Pepito", "GRILLO");
        item.setLastUpdate(Timestamp.valueOf("2019-01-01 00:00:00"));
        em.persist(item);
        item = new Actor(0, "Carmelo", "COTON");
        item.setLastUpdate(Timestamp.valueOf("2019-01-01 00:00:00"));
        em.persist(item);
        item = new Actor(0, "Capitan", "TAN");
        item.setLastUpdate(Timestamp.valueOf("2019-01-01 00:00:00"));
        em.persist(item);
        
        em.flush();
    }

    @Test
    void testGetAll_isNotEmpty() {
        var rslt = dao.findAll();
        assertThat(rslt.size()).isEqualTo(3);
    }
    
    @Test
    void testFindTop5ByFirstNameStartingWithOrderByLastNameDesc() {
        var rslt = dao.findTop5ByFirstNameStartingWithOrderByLastNameDesc("C");
        assertThat(rslt.size()).isEqualTo(2);
    }
    
    @Test
    void testFindTop5ByFirstNameStartingWith() {
        var rslt = dao.findTop5ByFirstNameStartingWith("C", Sort.by("lastName").descending());
        assertThat(rslt.size()).isEqualTo(2);
    }
    
    @Test
    void testFindByActorIdGreaterThan() {
        var rslt = dao.findByActorIdGreaterThan(0);
        assertThat(rslt.size()).isEqualTo(3);
    }
    
    @Test 
    void testFindNovedadesJPQL() {
        var rslt = dao.findNovedadesJPQL(0);
        assertThat(rslt.size()).isEqualTo(3);
    }
    
    @Test
    void testGetOne() {
        var actores = dao.findAll();
        if (!actores.isEmpty()) {
            var item = dao.findById(actores.get(0).getActorId());
            assertThat(item.isPresent()).isTrue();
        }
    }
    
    @Test
    void testFindBySQLTest() {
        var rslt = dao.findNovedadesSQL(0);
        assertThat(rslt.size()).isGreaterThan(0);
    }
    
    @Test
    void testQueryByActorIdGreaterThan() {
        var rslt = dao.queryByActorIdGreaterThan(0);
        assertThat(rslt.size()).isEqualTo(3);
    }
    
    @Test
    void testGetByActorIdGreaterThan() {
        var rslt = dao.getByActorIdGreaterThan(0);
        assertThat(rslt.size()).isEqualTo(3);
    }
}