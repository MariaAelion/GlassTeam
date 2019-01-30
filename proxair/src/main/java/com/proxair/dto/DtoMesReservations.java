package com.proxair.dto;

import java.sql.Time;
import java.util.Date;

import com.proxair.persistence.entity.Client;
import com.proxair.persistence.entity.Reservation;
import com.proxair.persistence.entity.Trajet;

public class DtoMesReservations {

	private Date date;
	private Time heureDepart;
	private String etatTrajet;
	private Client client;
	
	// mail (ou client)
	// montant total ttc
	// nbPlacesreservees
	
	public  DtoMesReservations(Trajet t, Reservation r) {
		this.setDate(t.getDate());
		this.setHeureDepart(t.getHeureDepart());
		this.setEtatTrajet(t.getEtatTrajet());
		this.setClient(r.getClient());
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	public String getEtatTrajet() {
		return etatTrajet;
	}

	public void setEtatTrajet(String etatTrajet) {
		this.etatTrajet = etatTrajet;
	}

	
	
	
}
