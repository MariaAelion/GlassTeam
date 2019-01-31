package com.proxair.dto;

import java.sql.Date;
import java.sql.Time;

public class DtoDemandedePaiement {
	private int idTrajet;
	private Date date;
	private Time time;
	private int nbPlacesReservees;
	private double prixPlaceTTC;
	private double prixTTC;
	public int getIdTrajet() {
		return idTrajet;
	}
	public void setIdTrajet(int idTrajet) {
		this.idTrajet = idTrajet;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getNbPlacesReservees() {
		return nbPlacesReservees;
	}
	public void setNbPlacesReservees(int nbPlacesReservees) {
		this.nbPlacesReservees = nbPlacesReservees;
	}
	public double getPrixPlaceTTC() {
		return prixPlaceTTC;
	}
	public void setPrixPlaceTTC(double prixPlaceTTC) {
		this.prixPlaceTTC = prixPlaceTTC;
	}
	public double getPrixTTC() {
		return prixTTC;
	}
	public void setPrixTTC(double prixTTC) {
		this.prixTTC = prixTTC;
	}
	
	

}
