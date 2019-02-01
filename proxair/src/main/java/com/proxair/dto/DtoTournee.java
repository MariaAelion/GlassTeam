package com.proxair.dto;

import java.sql.Time;

import com.proxair.persistence.entity.Tournee;

public class DtoTournee {
	
	private Time heureTournee;
	
	public DtoTournee() {}
	public DtoTournee(Tournee tournee) {
		this.setHeureTournee(tournee.getHeureTournee());
	}

	public Time getHeureTournee() {
		return heureTournee;
	}

	public void setHeureTournee(Time heureTournee) {
		this.heureTournee = heureTournee;
	}
}
