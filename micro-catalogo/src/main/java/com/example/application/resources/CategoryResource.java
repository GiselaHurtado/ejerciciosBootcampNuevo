package com.example.application.resources;



import com.example.domains.entities.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Schema(name = "Category", description = "Información de la categoría")
@Value
public class CategoryResource {
    @Schema(description = "Identificador de la categoría", accessMode = Schema.AccessMode.READ_ONLY)
    private int categoryId;
    
    @Schema(description = "Nombre de la categoría", example = "Acción")
    private String name;
    
    public static CategoryResource from(Category source) {
        return new CategoryResource(source.getCategoryId(), source.getName());
    }
}
