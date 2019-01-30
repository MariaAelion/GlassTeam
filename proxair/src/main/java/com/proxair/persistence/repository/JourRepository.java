package com.proxair.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxair.persistence.entity.Jour;

public interface JourRepository extends JpaRepository <Jour, Long>{

}
