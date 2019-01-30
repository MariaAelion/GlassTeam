package com.proxair.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private float montantTotalHT;
	
	@Column (name="etatReservation", nullable=false)
	private String etatReservation;
	
	@ManyToOne
	@JoinColumn(name="id_client", referencedColumnName="id")
	private Client client;

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

	public float getMontantTotalHT() {
		return montantTotalHT;
	}

	public void setMontantTotalTTC(float montantTotalHT) {
		this.montantTotalHT = montantTotalHT;
	}

	public String getEtatReservation() {
		return etatReservation;
	}

	public void setEtatReservation(String etatReservation) {
		this.etatReservation = etatReservation;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}	
}