package com.proxair.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proxair.persistence.entity.PrixGeneration;


public interface PrixGenerationRepository extends JpaRepository <PrixGeneration, Long>{
	
	@Query(value= "SELECT * FROM t_prixGeneration WHERE id = 1", nativeQuery = true)
	Optional<PrixGeneration> getPrixGeneration();
	
}
