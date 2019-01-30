package com.proxair.dto;

import java.sql.Time;

import com.proxair.persistence.entity.Trajet;

public class DtoTrajet {
	
	private Time heureDepart;
	private int nbPlacesTotal;
	private int nbPlacesDispo;
	private String etatDeplacement;
	
	public DtoTrajet() {
	}

	public DtoTrajet(Trajet trajet) {
		this.setHeureDepart(trajet.getHeureDepart());
		this.setNbPlacesTotal(trajet.getNbPlacesTotal());
		this.setNbPlacesDispo(trajet.getNbPlacesDispo());
		this.setEtatDeplacement(trajet.getEtatDeplacement());
	}

	public Time getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(Time heureDepart) {
		this.heureDepart = heureDepart;
	}

	public int getNbPlacesTotal() {
		return nbPlacesTotal;
	}

	public void setNbPlacesTotal(int nbPlacesTotal) {
		this.nbPlacesTotal = nbPlacesTotal;
	}

	public int getNbPlacesDispo() {
		return nbPlacesDispo;
	}

	public void setNbPlacesDispo(int nbPlacesDispo) {
		this.nbPlacesDispo = nbPlacesDispo;
	}

	public String getEtatDeplacement() {
		return etatDeplacement;
	}

	public void setEtatDeplacement(String etatDeplacement) {
		this.etatDeplacement = etatDeplacement;
	}
}
