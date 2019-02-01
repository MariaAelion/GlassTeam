package com.proxair.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_reservation")
public class Reservation {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column (name="id", unique=true, nullable=false)
	private long id;
	
	@Column (name="nbPlacesReservees", nullable=false)
	private int nbPlacesReservees;
	
	@Column (name="montantTotalTTC", nullable=false)
	private double montantTotalTTC;
	
	@Column (name="etatReservationClient", length = 20, nullable=false)
	private String etatReservationClient;
	
	@Column (name="etatPaiement", nullable=false)
	private boolean etatPaiement;
	
	@Column (name="mail", nullable=false)
	private String mail;
	

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public int getNbPlacesReservees() {
		return nbPlacesReservees;
	}



	public void setNbPlacesReservees(int nbPlacesReservees) {
		this.nbPlacesReservees = nbPlacesReservees;
	}



	public double getMontantTotalTTC() {
		return montantTotalTTC;
	}



	public void setMontantTotalTTC(double d) {
		this.montantTotalTTC = d;
	}



	public String getEtatReservationClient() {
		return etatReservationClient;
	}



	public void setEtatReservationClient(String etatReservationClient) {
		this.etatReservationClient = etatReservationClient;
	}


	public void setEtatPaiement(boolean etatPaiement) {
		this.etatPaiement = etatPaiement;
	}

	public boolean isEtatPaiement() {
		return etatPaiement;
	}

}