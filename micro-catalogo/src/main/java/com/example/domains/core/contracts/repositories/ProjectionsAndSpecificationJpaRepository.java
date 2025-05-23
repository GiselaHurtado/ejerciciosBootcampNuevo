package com.example.domains.core.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
//import org.springframework.data.rest.core.annotation.RestResource;

@NoRepositoryBean
public interface ProjectionsAndSpecificationJpaRepository<E, ID> 
	extends JpaRepository<E, ID>, JpaSpecificationExecutor<E>, RepositoryWithProjections {
//	@RestResource(exported = false)
	<T> List<T> findAllBy(Class<T> tipo);
//	@RestResource(exported = false)
	<T> List<T> findAllBy(Sort orden, Class<T> tipo);
//	@RestResource(exported = false)
	<T> Page<T> findAllBy(Pageable page, Class<T> tipo);
}