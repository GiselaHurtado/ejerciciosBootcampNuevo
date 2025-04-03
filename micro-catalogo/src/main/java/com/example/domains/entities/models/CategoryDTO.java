package com.example.domains.entities.models;

import com.example.domains.entities.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Schema(name = "Category", description = "Información de la categoría")
@Value
public class CategoryDTO {
    
    @Schema(description = "Identificador de la categoría", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;
    
    @Schema(description = "Nombre de la categoría", example = "Acción")
    private String categoria;
    
    public static CategoryDTO from(Category source) {
        return new CategoryDTO(source.getCategoryId(), source.getName());
    }
}

