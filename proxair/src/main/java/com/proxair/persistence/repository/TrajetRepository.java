package com.proxair.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proxair.persistence.entity.Trajet;

public interface TrajetRepository extends JpaRepository <Trajet, Long>{

	@Query(value= "SELECT * FROM t_trajet WHERE date = ?1 AND etat_reservation <> indisponible", nativeQuery = true)
	List<Trajet> findRidesByDate(Date date);
}