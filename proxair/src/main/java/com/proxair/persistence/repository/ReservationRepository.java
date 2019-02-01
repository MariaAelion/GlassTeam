package com.proxair.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.proxair.persistence.entity.Reservation;

public interface ReservationRepository extends JpaRepository <Reservation, Long>  {

	@Modifying(clearAutomatically = true)
    @Query (value = "UPDATE t_reservation SET etatReservationClient = 'Annulé' WHERE id = ?1", nativeQuery = true)
    void cancelReservation(long id);
	
	@Query (value = "SELECT id_trajets FROM t_reservation WHERE id = ?1", nativeQuery = true)
	Long findIdTrajetByIdReservation(long idReservation); 
}