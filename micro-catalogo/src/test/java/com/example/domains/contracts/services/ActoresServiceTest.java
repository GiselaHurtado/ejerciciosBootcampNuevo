package com.example.domains.contracts.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.InjectMocks;


import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.contracts.services.ActoresService;
import com.example.domains.entities.Actor;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;
import com.example.domains.services.ActoresServiceImpl;

import jakarta.validation.Validator;

class ActoresServiceTest {
    
   
	@MockBean
    private ActoresRepository dao;
    
    @MockBean
    private Validator validator;
    
    @InjectMocks
    private ActoresServiceImpl service;
    
    private Actor actor1, actor2;
    
    @BeforeEach
    void setUp() {
        dao = mock(ActoresRepository.class);
        validator = mock(Validator.class);
        service = new ActoresServiceImpl(dao);
        
        // Initialize test data
        actor1 = new Actor(1, "PENELOPE", "GUINESS");
        actor2 = new Actor(2, "NICK", "WAHLBERG");
    }
    
    @Test
    void testGetAll() {
        // Arrange
        List<Actor> expected = Arrays.asList(actor1, actor2);
        when(dao.findAll()).thenReturn(expected);
        
        // Act
        List<Actor> actual = service.getAll();
        
        // Assert
        assertIterableEquals(expected, actual);
        verify(dao, times(1)).findAll();
    }
    
    @Test
    void testGetOne_Found() {
        // Arrange
        when(dao.findById(1)).thenReturn(Optional.of(actor1));
        
        // Act
        Optional<Actor> actual = service.getOne(1);
        
        // Assert
        assertTrue(actual.isPresent());
        assertEquals(actor1, actual.get());
        verify(dao, times(1)).findById(1);
    }
    
    @Test
    void testGetOne_NotFound() {
        // Arrange
        when(dao.findById(99)).thenReturn(Optional.empty());
        
        // Act
        Optional<Actor> actual = service.getOne(99);
        
        // Assert
        assertFalse(actual.isPresent());
        verify(dao, times(1)).findById(99);
    }
    
    @Test
    void testAdd_Valid() throws DuplicateKeyException, InvalidDataException {
        // Arrange
        Actor newActor = new Actor(0, "EMMA", "STONE");
        when(dao.findById(0)).thenReturn(Optional.empty());
        when(dao.save(any(Actor.class))).thenReturn(new Actor(3, "EMMA", "STONE"));
        
        // Act
        Actor actual = service.add(newActor);
        
        // Assert
        assertEquals(3, actual.getActorId());
        assertEquals("EMMA", actual.getFirstName());
        assertEquals("STONE", actual.getLastName());
        verify(dao, times(1)).save(any(Actor.class));
    }
    
    @Test
    void testAdd_Duplicate() {
        // Arrange
        when(dao.findById(1)).thenReturn(Optional.of(actor1));
        
        // Act & Assert
        assertThrows(DuplicateKeyException.class, () -> service.add(actor1));
    }
    
    @Test
    void testAdd_Invalid() {
        // Arrange
        Actor invalidActor = new Actor(0, "", ""); // Invalid data
        
        // Act & Assert
        assertThrows(InvalidDataException.class, () -> service.add(invalidActor));
    }
    
    @Test
    void testModify_Valid() throws NotFoundException, InvalidDataException {
        // Arrange
        Actor modifiedActor = new Actor(1, "PENELOPE", "CRUZ");
        when(dao.findById(1)).thenReturn(Optional.of(actor1));
        when(dao.save(any(Actor.class))).thenReturn(modifiedActor);
        
        // Act
        Actor actual = service.modify(modifiedActor);
        
        // Assert
        assertEquals("CRUZ", actual.getLastName());
        verify(dao, times(1)).save(any(Actor.class));
    }
    
    @Test
    void testModify_NotFound() {
        // Arrange
        Actor nonExistentActor = new Actor(99, "UNKNOWN", "ACTOR");
        when(dao.findById(99)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(NotFoundException.class, () -> service.modify(nonExistentActor));
    }
    
    @Test
    void testModify_Invalid() {
        
        Actor invalidActor = new Actor(1, "", ""); // Invalid data
        when(dao.findById(1)).thenReturn(Optional.of(actor1));
        
      
        assertThrows(InvalidDataException.class, () -> service.modify(invalidActor));
    }
    
    @Test
    void testDelete() throws InvalidDataException, NotFoundException {
      
        doNothing().when(dao).delete(actor1);
        
       
        service.delete(actor1);
        
        
        verify(dao, times(1)).delete(actor1);
    }
    
    @Test
    void testDeleteById() throws InvalidDataException, NotFoundException {
       
        doNothing().when(dao).deleteById(1);
        
    
        service.deleteById(1);
        
        
        verify(dao, times(1)).deleteById(1);
    }
    
   
}
