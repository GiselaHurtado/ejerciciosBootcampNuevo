package com.example.domains.contracts.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.domains.core.contracts.services.DomainService;
import com.example.domains.entities.Category;
import com.example.domains.entities.models.CategoryDTO;

public interface CategoryService extends DomainService<Category, Integer> {
	List<Category> novedades(Timestamp fecha);

	List<CategoryDTO> getByProjection(Class<CategoryDTO> class1);

	Page<CategoryDTO> getByProjection(Pageable pageable, Class<CategoryDTO> class1);
}