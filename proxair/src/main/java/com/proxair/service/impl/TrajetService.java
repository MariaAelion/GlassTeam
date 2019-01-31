package com.proxair.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxair.exception.NotFoundException;
import com.proxair.persistence.entity.Reservation;
import com.proxair.persistence.entity.Trajet;
import com.proxair.persistence.repository.TrajetRepository;
import com.proxair.service.ITrajetService;

@Service
@Transactional
public class TrajetService implements ITrajetService {
	
	@Autowired TrajetRepository trajetRepository;
	
	public void updateNbPlacesTrajet(long idTrajet) {
		Optional<Trajet> trajet = trajetRepository.findById(idTrajet);
		
		if (trajet.isPresent()) {
		List<Reservation> listReservations = trajet.get().getReservations();
		int nbTotalPlacesReservees = 0;
		
			for (Reservation r : listReservations) {
				if (r.isEtatPaiement() == true && r.getEtatReservationClient() != "Annulé") {
					nbTotalPlacesReservees += r.getNbPlacesReservees();
				}
		}
		trajet.get().setNbPlacesDispo(trajet.get().getNbPlacesTotal()-nbTotalPlacesReservees);
		trajetRepository.updatePlacesDispos(trajet.get().getNbPlacesDispo(), idTrajet);
	}
		else {
			throw new NotFoundException ("Mise à jour des places disponibles sur trajet inexistant !");
		}
	}
	
	public void updateEtatReservation(long idTrajet) {
		Optional<Trajet> trajet = trajetRepository.findById(idTrajet);
		if (trajet.isPresent()) {
			trajet.get().setEtatReservation("Disponible");
			if (trajet.get().getNbPlacesDispo() == 0) {
				trajet.get().setEtatReservation("Complet");
			}
			trajetRepository.updateEtatReservation(trajet.get().getEtatReservation(), idTrajet);
			}
		else {
			throw new NotFoundException ("Mise à jour de l'état de réservation sur trajet inexistant !");
		}
	}
}