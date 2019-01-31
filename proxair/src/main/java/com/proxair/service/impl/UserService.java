package com.proxair.service.impl;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxair.dto.DtoReservationPlaces;
import com.proxair.dto.DtoTarifAttributs;

import com.proxair.dto.DtoTrajet;
import com.proxair.exception.NotFoundException;
import com.proxair.persistence.entity.Trajet;
import com.proxair.persistence.repository.ReservationRepository;
import com.proxair.persistence.repository.TrajetRepository;
import com.proxair.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired TrajetRepository trajetRepository;
	@Autowired ReservationRepository reservationRepository;
	@Autowired TrajetService trajetService;

	public List<DtoTrajet> findRides(Date date) {
		List<Trajet> trajets = trajetRepository.findRidesByDate(date);
		
		return trajets.stream()
				.map(trajet -> new DtoTrajet(trajet))
				.collect(Collectors.toList());
		}
	
	@Override
    public void cancelResa(long idReservation) {
        reservationRepository.cancelReservation(idReservation); 
        trajetService.updateNbPlacesTrajet(reservationRepository.findIdTrajetByIdReservation(idReservation));
        trajetService.updateEtatReservation(reservationRepository.findIdTrajetByIdReservation(idReservation));
    }
	
	public DtoReservationPlaces chooseSeats(long idTrajet, int nbrePlaces) {
		Optional<Trajet> trajet = trajetRepository.findById(idTrajet);
		
		if (trajet.isPresent() && trajet.get().getEtatReservation()!="Complet" && trajet.get().getEtatTrajet()!="Annulé") {
			
			if (nbrePlaces > trajet.get().getNbPlacesDispo()) {
				throw new NotFoundException (" Nombre de places demandées superieur au nombre réel de places disponibles");
			}
			
			DtoReservationPlaces drp = new DtoReservationPlaces();
			drp.setEtatPaiement(false);
			drp.setNbPlacesReservees(nbrePlaces);
			drp.setMontantTotalTTC(nbrePlaces*DtoTarifAttributs.TARIFTTC); 	
			
			return drp;
		}
		else {
			throw new NotFoundException ("Trajet indisponible à la réservation ou inexistant !");
		}
	}
	
	
	public void Registerreservation() {}
	
	
	
	
	public boolean CheckifReservationIsPossible(Date date, Time time, int nbPlaceAReserver) {
		
		Optional<Trajet> opt = trajetRepository.findRide(date,time);
		if (opt.isPresent()) {
			if(opt.get().getNbPlacesDispo() >= nbPlaceAReserver) {
				return true;
			} else return false;
		
		} else {
			throw new NotFoundException(" Le trajet qui part le" + date.toString() + " à " + time.toString() +" n'a pas été trouvé !");
		}
	}
	
	public boolean CheckifReservationIsPossible(int idTrajet, int nbPlaceAReserver) {
		
		Optional<Trajet> opt = trajetRepository.findRide(idTrajet);
		if (opt.isPresent()) {
			if(opt.get().getNbPlacesDispo() >= nbPlaceAReserver) {
				return true;
			} else return false;
		
		} else {
			throw new NotFoundException(" Le trajet " + idTrajet + " n'a pas été trouvé !");
		}
	}
}