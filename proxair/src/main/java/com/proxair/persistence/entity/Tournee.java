package com.proxair.persistence.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_tournee")
public class Tournee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "heureTournee", nullable = false)
	private Time heureTournee;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Time getHeureTournee() {
		return heureTournee;
	}

	public void setHeureTournee(Time heureTournee) {
		this.heureTournee = heureTournee;
	}
	
}

