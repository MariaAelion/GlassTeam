package com.proxair.dto;

import java.sql.Date;
import java.sql.Time;

public class DtoCreationTrajet {
	
	
	private Date date;
	private Time heureDepart;
	private int nbPlacesTotal;
	
	public DtoCreationTrajet() {}
	
	public DtoCreationTrajet(Date date, Time heureDepart, int nbPlacesTotal) {
		this.setDate(date);
		this.setHeureDepart(heureDepart);
		this.setNbPlacesTotal(nbPlacesTotal);
	}
	
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
	public int getNbPlacesTotal() {
		return nbPlacesTotal;
	}
	public void setNbPlacesTotal(int nbPlacesTotal) {
		this.nbPlacesTotal = nbPlacesTotal;
	}

}
