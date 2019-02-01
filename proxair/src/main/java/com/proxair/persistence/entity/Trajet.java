package com.proxair.persistence.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

	@Entity
	@Table(name = "t_trajet")
	public class Trajet {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		private long id;
		
		@Column(name = "date", nullable = false)
		private Date date;

		@Column(name = "heureDepart", nullable = false)
		private Time heureDepart;

		@Column(name = "nbPlacesTotal", nullable = false)
		private int nbPlacesTotal;
		
		@Column(name = "nbPlacesDispo", nullable = false)
		private int nbPlacesDispo;
		
		@Column(name = "etat_trajet", nullable = false)
		private String etatTrajet;
		
		@Column(name = "etat_reservation", nullable = false)
		private String etatReservation;
		
		@Column(name = "prix_place", nullable = false)
        private double prix_place;

        @Column(name = "tva", nullable = false)
        private double tva;
		
		@OneToMany
		@JoinColumn(name="id_trajets", referencedColumnName ="id")
		private List<Reservation> reservations;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
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

		public int getNbPlacesDispo() {
			return nbPlacesDispo;
		}

		public void setNbPlacesDispo(int nbPlacesDispo) {
			this.nbPlacesDispo = nbPlacesDispo;
		}

		public String getEtatTrajet() {
			return etatTrajet;
		}

		public void setEtatTrajet(String etatTrajet) {
			this.etatTrajet = etatTrajet;
		}

		public String getEtatReservation() {
			return etatReservation;
		}

		public void setEtatReservation(String etatReservation) {
			this.etatReservation = etatReservation;
		}

		public double getPrix_place() {
			return prix_place;
		}

		public void setPrix_place(double prix_place) {
			this.prix_place = prix_place;
		}

		public double getTva() {
			return tva;
		}

		public void setTva(double tva) {
			this.tva = tva;
		}

		public List<Reservation> getReservations() {
			return reservations;
		}

		public void setReservations(List<Reservation> reservations) {
			this.reservations = reservations;
		}

}