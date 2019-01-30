package com.proxair.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.proxair.persistence.entity.Reservation;

public interface ReservationRepository extends JpaRepository <Reservation, Long>  {
	
	@Modifying(clearAutomatically = true)
	@Query (value = "UPDATE t_reservation SET etatReservation = 'Annulé' WHERE id = ?1", nativeQuery = true)
	void cancelReservation(long id);
}
