package com.proxair.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxair.persistence.entity.Client;

public interface ClientRepository extends JpaRepository <Client, Long>{

}