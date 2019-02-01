package com.proxair.dto;

import java.sql.Date;

public class DtoGenerate {
	private Date date;
	private int nombreDeJours;
	private int nombreDePlaces;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNombreDeJours() {
		return nombreDeJours;
	}
	public void setNombreDeJours(int nombreDeJours) {
		this.nombreDeJours = nombreDeJours;
	}
	public int getNombreDePlaces() {
		return nombreDePlaces;
	}
	public void setNombreDePlaces(int nombreDePlaces) {
		this.nombreDePlaces = nombreDePlaces;
	}

}
