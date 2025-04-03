package com.example.domains.entities.models;



import com.example.domains.entities.Language;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Schema(name = "Language", description = "Información del idioma")
@Value
public class LanguageDTO {
    @Schema(description = "Identificador del idioma", accessMode = Schema.AccessMode.READ_ONLY)
    private int languageId;
    
    @Schema(description = "Nombre del idioma", example = "English")
    private String name;
    
    public static LanguageDTO from(Language source) {
        return new LanguageDTO(source.getLanguageId(), source.getName());
    }
}
