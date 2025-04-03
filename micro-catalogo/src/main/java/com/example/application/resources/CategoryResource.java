package com.example.application.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.models.CategoryDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categorias/v1")
@Tag(name = "category-service", description = "Gestión de categorías")
public class CategoryResource {

    private final CategoryService srv;

    public CategoryResource(CategoryService srv) {
        this.srv = srv;
    }
    
    @GetMapping
    @Hidden
    public List<CategoryDTO> getAll() {
        return srv.getByProjection(CategoryDTO.class);
    }
    
    @GetMapping(params = { "page" })
    @Operation(summary = "Obtiene todas las categorías paginadas")
    public Page<CategoryDTO> getAll(@ParameterObject Pageable pageable) {
        return srv.getByProjection(pageable, CategoryDTO.class);
    }
    
    @GetMapping(path = "/{id}")
    @Operation(summary = "Obtiene una categoría por su id")
    public CategoryDTO getOne(@PathVariable @Parameter(description = "Identificador de la categoría") int id)
            throws NotFoundException {
        Optional<?> item = srv.getOne(id);
        if (item.isEmpty()) {
            throw new NotFoundException("No se encontró la categoría con id " + id);
        }
        
        return CategoryDTO.from((com.example.domains.entities.Category)item.get());
    }
    
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Categoría creada")
    public ResponseEntity<Object> create(@Valid @RequestBody CategoryDTO item)
            throws BadRequestException, DuplicateKeyException, InvalidDataException {
        var newItem = srv.add(com.example.domains.entities.Category.builder()
                .name(item.getCategoria())
                .build());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newItem.getCategoryId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Modifica una categoría existente")
    public void update(@PathVariable int id, @Valid @RequestBody CategoryDTO item)
            throws BadRequestException, NotFoundException, InvalidDataException {
        if (item.getId() != id) {
            throw new BadRequestException("El id de la categoría no coincide con el recurso a modificar");
        }
        srv.modify(com.example.domains.entities.Category.builder()
                .categoryId(item.getId())
                .name(item.getCategoria())
                .build());
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Borra una categoría existente")
    public void delete(@PathVariable int id) throws NotFoundException, InvalidDataException {
        srv.deleteById(id);
    }
}
