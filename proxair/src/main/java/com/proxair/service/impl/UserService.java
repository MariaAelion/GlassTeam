package com.proxair.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxair.dto.DtoDemandedePaiement;
import com.proxair.dto.DtoTrajet;
import com.proxair.dto.DtodemandeAchat;
import com.proxair.exception.NotFoundException;
import com.proxair.persistence.entity.Trajet;
import com.proxair.persistence.repository.TrajetRepository;
import com.proxair.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired TrajetRepository trajetRepository;
	
	public List<DtoTrajet> findRides(Date date) {
		List<Trajet> trajets = trajetRepository.findRidesByDate(date);
		
		return trajets.stream()
				.map(trajet -> new DtoTrajet(trajet))
				.collect(Collectors.toList());
		}
	
	
	
	public void registerreservation() {}
	
	public DtoDemandedePaiement askReservationFromUser(DtodemandeAchat dtodemandeAchat) {
		
		if(checkifReservationIsPossible(dtodemandeAchat)) {
			DtoDemandedePaiement dtoDemandedePaiement = new DtoDemandedePaiement();
			Optional<Trajet> trajet = trajetRepository.findRide(dtodemandeAchat.getDate(), dtodemandeAchat.getHeureDepart());
			dtoDemandedePaiement.setDate(dtodemandeAchat.getDate());
			dtoDemandedePaiement.setIdTrajet(trajet.get().getId());
			dtoDemandedePaiement.setNbPlacesReservees(dtodemandeAchat.getNbPlacesDemandees());
			dtoDemandedePaiement.setPrixPlace(trajet.get().getPrix_place());
			dtoDemandedePaiement.setPrixTTC((double)(trajet.get().getPrix_place()* dtodemandeAchat.getNbPlacesDemandees()));
			dtoDemandedePaiement.setTime(dtodemandeAchat.getHeureDepart());
			return dtoDemandedePaiement;
		}else {
			throw new NotFoundException(" Le trajet qui part le" + dtodemandeAchat.getDate().toString() + " à " + dtodemandeAchat.getHeureDepart().toString() +" n'est plus disponible !");
		}
			
		
		
	}
	
	
	public boolean checkifReservationIsPossible(DtodemandeAchat dtodemandeAchat) {
		
		Optional<Trajet> opt = trajetRepository.findRide(dtodemandeAchat.getDate(),dtodemandeAchat.getHeureDepart());
		if (opt.isPresent()) {
			if(opt.get().getNbPlacesDispo() >= dtodemandeAchat.getNbPlacesDemandees()) {
				return true;
			} else return false;
		
		} else {
			throw new NotFoundException(" Le trajet qui part le" + dtodemandeAchat.getDate().toString() + " à " + dtodemandeAchat.getHeureDepart() +" n'a pas été trouvé !");
		}
	}
	
	public boolean checkifReservationIsPossible(int idTrajet, int nbPlaceAReserver) {
		
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