package com.proxair.persistence.repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.proxair.persistence.entity.Trajet;

public interface TrajetRepository extends JpaRepository <Trajet, Long>{

	@Query(value= "SELECT * FROM t_trajet WHERE date = ?1 AND etat_reservation <> 'indisponible', nativeQuery = true")
	List<Trajet> findRidesByDate(Date date);
	
	@Modifying(clearAutomatically = true)
    @Query (value = "UPDATE t_trajet SET etat_trajet = 'Validé' WHERE id = ?1", nativeQuery = true)
	void valideTrajet(long id);
	
	@Modifying(clearAutomatically = true)
    @Query (value = "UPDATE t_trajet SET nbPlacesDispo = ?1 WHERE id = ?2", nativeQuery = true)
	void updatePlacesDispos(int nbPlacesDispos, long idTrajet);
	
	@Modifying(clearAutomatically = true)
    @Query (value = "UPDATE t_trajet SET etat_reservation = ?1 WHERE id = ?2", nativeQuery = true)
	void updateEtatReservation(String etatReservation, long idTrajet);

	@Query(value= "SELECT * FROM t_trajet WHERE id = ?1", nativeQuery = true)
	Optional<Trajet> findRide(long idTrajet);
	
	@Query(value= "SELECT * FROM t_trajet WHERE date = ?1 AND heureDepart=?2", nativeQuery = true)
	Optional<Trajet> findRide(Date date, Time heureDepart);
	
	@Query(value= "SELECT * FROM t_trajet WHERE date = ?1 AND heureDepart BETWEEN ?2 AND ?3", nativeQuery = true)
	List<Trajet> findRidesBetween(Date date, Time time1, Time time2);
	
}