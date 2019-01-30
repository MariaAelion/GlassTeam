package com.proxair.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxair.persistence.entity.Reservation;

public interface ReservationRepository extends JpaRepository <Reservation, Long>  {

}
