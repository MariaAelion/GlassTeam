package com.proxair.dto;

public class DtoReservationPlaces {
	
	private int nbPlacesReservees;
	private double montantTotalTTC;
	private String etatReservationClient;
	private boolean etatPaiement;
	private long idTrajet;
	
	public DtoReservationPlaces() {
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

	public void setMontantTotalTTC(double montantTotalTTC) {
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

	public long getIdTrajet() {
		return idTrajet;
	}

	public void setIdTrajet(long idTrajet) {
		this.idTrajet = idTrajet;
	}


}
