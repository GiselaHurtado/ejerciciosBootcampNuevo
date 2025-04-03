package com.example.application.resources;



import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.Language;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/languages/v1")
@Tag(name = "language-service", description = "Gestión de idiomas")
public class LanguageResource {

    private final LanguageService srv;

    public LanguageResource(LanguageService srv) {
        this.srv = srv;
    }
    
    // Para obtener todos los idiomas sin paginación
    @GetMapping
    @Hidden
    public List<Language> getAll() {
        return srv.getAll();
    }
    
    // Si tu servicio soporta paginación, por ejemplo a través de DomainService o un método adicional
    @GetMapping(params = "page")
    @Operation(summary = "Obtiene los idiomas paginados")
    public Page<Language> getAll(@ParameterObject Pageable pageable) {
        // Si tu LanguageService no tiene un método paginado, podrías convertir la lista completa a una Page.
        List<Language> list = srv.getAll();
        // Ejemplo simple: se crea una PageImpl con la lista (no es óptimo para grandes volúmenes)
        return new org.springframework.data.domain.PageImpl<>(list, pageable, list.size());
    }
    
    @GetMapping(path = "/{id}")
    @Operation(summary = "Obtiene un idioma por su id")
    public Language getOne(@PathVariable @Parameter(description = "Identificador del idioma") int id)
            throws NotFoundException {
        Optional<Language> lang = srv.getOne(id);
        if (lang.isEmpty()) {
            throw new NotFoundException("No se encontró el idioma con id " + id);
        }
        return lang.get();
    }
    
    @PostMapping
    @Operation(summary = "Crea un nuevo idioma")
    public ResponseEntity<Object> create(@Valid @RequestBody Language lang) throws BadRequestException, DuplicateKeyException, InvalidDataException {
        Language newLang = srv.add(lang);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newLang.getLanguageId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Modifica un idioma existente")
    public void update(@PathVariable int id, @Valid @RequestBody Language lang)
            throws BadRequestException, NotFoundException, InvalidDataException {
        if (lang.getLanguageId() != id) {
            throw new BadRequestException("El id del idioma no coincide con el recurso a modificar");
        }
        srv.modify(lang);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Borra un idioma existente")
    public void delete(@PathVariable int id) throws NotFoundException, InvalidDataException {
        srv.deleteById(id);
    }
}
