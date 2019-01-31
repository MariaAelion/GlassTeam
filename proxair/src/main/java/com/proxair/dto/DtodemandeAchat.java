package com.proxair.dto;

import java.sql.Date;
import java.sql.Time;

public class DtodemandeAchat {
	
	private Date date;
	private Time heureDepart;
	private int nbPlacesDemandees;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getHeureDepart() {
		return heureDepart;
	}
	public void setHeureDepart(Time heureDepart) {
		this.heureDepart = heureDepart;
	}
	public int getNbPlacesDemandees() {
		return nbPlacesDemandees;
	}
	public void setNbPlacesDemandees(int nbPlacesDemandees) {
		this.nbPlacesDemandees = nbPlacesDemandees;
	}

	
}
