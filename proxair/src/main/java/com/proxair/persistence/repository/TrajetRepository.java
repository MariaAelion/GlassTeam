package com.proxair.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.proxair.persistence.entity.Trajet;

public interface TrajetRepository extends JpaRepository <Trajet, Long>{

	@Query(value= "SELECT * FROM t_trajet WHERE id_jour = ?1", nativeQuery = true)
	List<Trajet> findRidesByDate(Date date);
	
	@Modifying(clearAutomatically = true)
	@Query (value = "UPDATE t_trajet SET etat_trajet = 'Annulé' WHERE id = ?1", nativeQuery = true)
void cancelTrajet(long id);
	
	

}