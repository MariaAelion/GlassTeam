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
	private float montantTotalTTC;
	
	@Column (name="etatReservationClient", nullable=false)
	private String etatReservationClient;
	
	@Column (name="etatPaiement", nullable=false)
	private boolean etatPaiement;
	
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



	public float getMontantTotalTTC() {
		return montantTotalTTC;
	}



	public void setMontantTotalTTC(float montantTotalTTC) {
		this.montantTotalTTC = montantTotalTTC;
	}



	public String getEtatReservationClient() {
		return etatReservationClient;
	}



	public void setEtatReservationClient(String etatReservationClient) {
		this.etatReservationClient = etatReservationClient;
	}



	public boolean isEtatPaiement() {
		return etatPaiement;
	}



	public void setEtatPaiement(boolean etatPaiement) {
		this.etatPaiement = etatPaiement;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}


}