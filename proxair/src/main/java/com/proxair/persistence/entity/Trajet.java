package com.proxair.persistence.entity;

import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

	@Entity
	@Table(name = "t_trajet")
	public class Trajet {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		private long id;

		@Column(name = "heureDepart", nullable = false)
		private Time heureDepart;

		@Column(name = "nbPlacesTotal", nullable = false)
		private int nbPlacesTotal;
		
		@Column(name = "nbPlacesDispo", nullable = false)
		private int nbPlacesDispo;
		
		@Column(name = "etat_trajet", nullable = false)
		private String etatTrajet;

		@ManyToOne
		@JoinColumn(name="id_jour", referencedColumnName = "id")
		private Jour jour;
		
		@OneToMany
		@JoinColumn(name="id_reservations", referencedColumnName ="id")
		private List<Reservation> reservations;
		
		@ManyToOne
		@JoinColumn(name="id_vehicule", referencedColumnName="id")
		private Vehicule vehicule;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
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

		public String getEtatDeplacement() {
			return etatTrajet;
		}

		public void setEtatDeplacement(String etatDeplacement) {
			this.etatTrajet = etatDeplacement;
		}

		public Jour getJour() {
			return jour;
		}

		public void setJour(Jour jour) {
			this.jour = jour;
		}

		public List<Reservation> getReservations() {
			return reservations;
		}

		public void setReservations(List<Reservation> reservations) {
			this.reservations = reservations;
		}

		public Vehicule getVehicule() {
			return vehicule;
		}

		public void setVehicule(Vehicule vehicule) {
			this.vehicule = vehicule;
		}
}