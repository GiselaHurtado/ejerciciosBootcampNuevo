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
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.entities.Actor;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@ExtendWith(MockitoExtension.class)
class ActorServiceImplTest {

    @Mock
    private ActoresRepository dao;

    @InjectMocks
    private ActoresServiceImpl srv;

    private List<Actor> listaActores;

    @BeforeEach
    void setUp() {
        listaActores = Arrays.asList(
                new Actor(1, "Robert", "DICAPRIO"),
                new Actor(2, "Nicole", "KIDMAN"),
                new Actor(3, "Angelina", "JOLIE")
        );
    }

    @Test
    void testGetAll_isNotEmpty() {
        when(dao.findAll()).thenReturn(listaActores);
        var rslt = srv.getAll();
        assertThat(rslt).hasSize(3);
        verify(dao, times(1)).findAll();
    }

    @Test
    void testGetOne_valid() {
        when(dao.findById(1)).thenReturn(Optional.of(listaActores.get(0)));
        var rslt = srv.getOne(1);
        assertThat(rslt).isPresent();
    }

    @Test
    void testGetOne_notFound() {
        when(dao.findById(99)).thenReturn(Optional.empty());
        var rslt = srv.getOne(99);
        assertThat(rslt).isEmpty();
    }

    @Test
    void testAddKO_NullActor() {
        assertThrows(InvalidDataException.class, () -> srv.add(null));
        verify(dao, never()).save(any());
    }

    @Test
    void testAddDuplicateKeyKO() {
        when(dao.existsById(1)).thenReturn(true);
        assertThrows(DuplicateKeyException.class, () -> srv.add(new Actor(1, "RR", "PRIO")));
    }

    @Test
    void testDelete_NotFound() {
        when(dao.existsById(99)).thenReturn(false);
        assertThrows(NotFoundException.class, () -> srv.deleteById(99));
    }

    @Test
    void testRepartePremios() {
        when(dao.findAll()).thenReturn(listaActores);
        srv.repartePremios();
        verify(dao, times(1)).findAll();
    }

    @Test
    void testRepartePremios_SinActores() {
        when(dao.findAll()).thenReturn(Arrays.asList());
        srv.repartePremios();
        verify(dao, times(1)).findAll();
    }
}


}