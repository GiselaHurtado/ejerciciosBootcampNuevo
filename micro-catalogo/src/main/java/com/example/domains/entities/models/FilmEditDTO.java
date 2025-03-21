package com.example.domains.entities.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.domains.entities.Film;
import com.example.domains.entities.Film.SpecialFeature;
import com.example.domains.entities.Language;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmEditDTO {
    private int filmId;
    private String description;
    private Integer length;

   

    private Short releaseYear;

    @NotNull
    private Byte rentalDuration;

    @NotNull
    private BigDecimal rentalRate;

    @NotNull
    private BigDecimal replacementCost;

    @NotBlank
    @Size(min = 2, max = 128)
    private String title;

    @NotNull
    private Integer languageId;

    private Integer languageVOId;

    private List<String> specialFeatures = new ArrayList<>();
    private List<Integer> actors = new ArrayList<>();
    private List<Integer> categories = new ArrayList<>();

    public static FilmEditDTO from(Film source) {
        return new FilmEditDTO(
                source.getFilmId(),
                source.getDescription(),
                source.getLength(),
                source.getReleaseYear(),
                (byte) source.getRentalDuration(),
                source.getRentalRate(),
                source.getReplacementCost(),
                source.getTitle(),
                source.getLanguage() == null ? null : source.getLanguage().getLanguageId(),
                source.getLanguageVO() == null ? null : source.getLanguageVO().getLanguageId(),
                source.getSpecialFeatures() == null ? new ArrayList<>() :
                        source.getSpecialFeatures().stream()
                                .filter(Objects::nonNull)
                                .map(SpecialFeature::getValue)
                                .sorted()
                                .collect(Collectors.toList()),
                source.getActors() == null ? new ArrayList<>() :
                        source.getActors().stream()
                                .map(actor -> actor.getActorId())
                                .collect(Collectors.toList()),
                source.getCategories() == null ? new ArrayList<>() :
                        source.getCategories().stream()
                                .map(category -> category.getCategoryId())
                                .collect(Collectors.toList())
        );
    }

    	
   
   
    }