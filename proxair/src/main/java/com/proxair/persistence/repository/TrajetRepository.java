package com.proxair.persistence.repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.proxair.persistence.entity.Trajet;

public interface TrajetRepository extends JpaRepository <Trajet, Long>{

	@Query(value= "SELECT * FROM t_trajet WHERE date = ?1", nativeQuery = true)
	List<Trajet> findRidesByDate(Date date);
	
}
	@Query(value= "SELECT * FROM t_trajet WHERE id = ?1", nativeQuery = true)
	Optional<Trajet> findRide(int id);
	
	@Query(value= "SELECT * FROM t_trajet WHERE date = ?1 AND heureDepart=?2", nativeQuery = true)
	Optional<Trajet> findRide(Date date, Time heureDepart);
	
	@Query(value= "SELECT * FROM t_trajet WHERE date = ?1 AND heureDepart BETWEEN ?2 AND ?3", nativeQuery = true)
	List<Trajet> findRidesBetween(Date date, Time time1, Time time2);

}
