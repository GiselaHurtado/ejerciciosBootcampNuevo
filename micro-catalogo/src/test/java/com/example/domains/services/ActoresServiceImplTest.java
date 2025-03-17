package com.example.domains.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.entities.Actor;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@ExtendWith(MockitoExtension.class)
class ActoresServiceImplTest {

    @Mock
    private ActoresRepository dao;

    @InjectMocks
    private ActoresServiceImpl service;

    private Actor actor1, actor2, actor3;
    private List<Actor> listaActores;

    @BeforeEach
    void setUp() {
       
        actor1 = new Actor(1, "PENELOPE", "GUINESS");
        actor2 = new Actor(2, "NICK", "WAHLBERG");
        actor3 = new Actor(3, "ED", "CHASE");
        listaActores = Arrays.asList(actor1, actor2, actor3);
    }

    @Test
    void testGetAll_isNotEmpty() {
        when(dao.findAll()).thenReturn(listaActores);
        var rslt = service.getAll();
        assertThat(rslt).hasSize(3);
        verify(dao, times(1)).findAll();
    }

    @Test
    void testGetOne_valid() {
        when(dao.findById(1)).thenReturn(Optional.of(actor1));
        var rslt = service.getOne(1);
        assertThat(rslt).isPresent();
        assertThat(rslt.get()).isEqualTo(actor1);
    }

    @Test
    void testGetOne_notFound() {
        when(dao.findById(99)).thenReturn(Optional.empty());
        var rslt = service.getOne(99);
        assertThat(rslt).isEmpty();
    }

    @Test
    void testAdd_Valid() throws DuplicateKeyException, InvalidDataException {
        Actor newActor = new Actor(4, "EMMA", "STONE");
        when(dao.existsById(4)).thenReturn(false);
        when(dao.save(any(Actor.class))).thenReturn(new Actor(4, "EMMA", "STONE"));
        
        Actor result = service.add(newActor);
        
        assertThat(result.getFirstName()).isEqualTo("EMMA");
        assertThat(result.getLastName()).isEqualTo("STONE");
        verify(dao, times(1)).save(any(Actor.class));
    }

    @Test
    void testAddKO_NullActor() {
        assertThrows(InvalidDataException.class, () -> service.add(null));
        verify(dao, never()).save(any());
    }

    @Test
    void testAddDuplicateKeyKO() {
        when(dao.existsById(1)).thenReturn(true);
        assertThrows(DuplicateKeyException.class, () -> service.add(new Actor(1, "RR", "PRIO")));
    }
    
    @Test
    void testModify_Valid() throws NotFoundException, InvalidDataException {
        Actor modifiedActor = new Actor(1, "PENELOPE", "CRUZ");
        when(dao.existsById(1)).thenReturn(true);
        when(dao.save(any(Actor.class))).thenReturn(modifiedActor);
        
        Actor result = service.modify(modifiedActor);
        
        assertThat(result.getLastName()).isEqualTo("CRUZ");
        verify(dao, times(1)).save(any(Actor.class));
    }
    
    @Test
    void testModify_NotFound() {
        Actor nonExistentActor = new Actor(99, "UNKNOWN", "ACTOR");
        when(dao.existsById(99)).thenReturn(false);
        
        assertThrows(NotFoundException.class, () -> service.modify(nonExistentActor));
    }
    
    @Test
    void testModify_Invalid() {
        Actor invalidActor = null;
        
        assertThrows(InvalidDataException.class, () -> service.modify(invalidActor));
    }
    
    @Test
    void testDelete() throws InvalidDataException {
        doNothing().when(dao).delete(actor1);
        
        service.delete(actor1);
        
        verify(dao, times(1)).delete(actor1);
    }
    
    @Test
    void testDeleteById() throws InvalidDataException {
        doNothing().when(dao).deleteById(1);
        
        service.deleteById(1);
        
        verify(dao, times(1)).deleteById(1);
    }
    
 

    @Test
    void testRepartePremios() {
        when(dao.findAll()).thenReturn(listaActores);
        service.repartePremios();
        verify(dao, times(1)).findAll();
    }

    @Test
    void testRepartePremios_SinActores() {
        when(dao.findAll()).thenReturn(Arrays.asList());
        service.repartePremios();
        verify(dao, times(1)).findAll();
    }
}