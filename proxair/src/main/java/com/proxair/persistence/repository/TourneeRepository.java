package com.proxair.persistence.repository;

import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proxair.persistence.entity.Tournee;

public interface TourneeRepository extends JpaRepository <Tournee, Long>{
	
	@Query(value= "SELECT * FROM t_tournee WHERE heureTournee BETWEEN ?1 AND ?2", nativeQuery = true)
	List<Tournee> findTripBetween(Time time1, Time time2);
	
	@Query(value= "SELECT * FROM t_tournee ORDER BY heureTournee", nativeQuery = true)
	List<Tournee> findAllOrdered();

}
