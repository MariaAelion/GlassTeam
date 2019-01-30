package com.proxair.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxair.persistence.entity.Vehicule;

public interface VehiculeRepository extends JpaRepository <Vehicule, Long>{

}
