package com.proxair.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.internet.AddressException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxair.dto.DtoMail;
import com.proxair.dto.DtoMailAttributs;
import com.proxair.dto.DtoReservationPlaces;
import com.proxair.dto.DtoTarifAttributs;

import com.proxair.dto.DtoDemandedePaiement;

import com.proxair.dto.DtoTrajet;
import com.proxair.dto.DtodemandeAchat;
import com.proxair.exception.NotFoundException;
import com.proxair.persistence.entity.Reservation;
import com.proxair.persistence.entity.Trajet;
import com.proxair.persistence.repository.ReservationRepository;
import com.proxair.persistence.repository.TrajetRepository;
import com.proxair.service.ITrajetService;
import com.proxair.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired ITrajetService trajetService;
	@Autowired TrajetRepository trajetRepository;
	@Autowired ReservationRepository reservationRepository;
	@Autowired EmailService emailService;


	public List<DtoTrajet> findRides(Date date) {
		List<Trajet> trajets = trajetRepository.findRidesByDate(date);
		
		return trajets.stream()
				.map(trajet -> new DtoTrajet(trajet))
				.collect(Collectors.toList());
		}
	
	@Override
    public void cancelResa(long idReservation) {
		Optional<Reservation> reservation = reservationRepository.findById(idReservation);
		
		if (reservation.isPresent()) {
			 reservationRepository.cancelReservation(idReservation); 
		     trajetService.updateNbPlacesTrajet(reservationRepository.findIdTrajetByIdReservation(idReservation));
		     trajetService.updateEtatReservation(reservationRepository.findIdTrajetByIdReservation(idReservation));
		     
		     DtoMail email = new DtoMail();
				email.setContent("Nous vous confirmons l'annulation de votre réservation ("+reservation.get().getNbPlacesReservees()+" place(s) réservées) pour le trajet Saint-Etienne/Lyon. Vous serez remboursés du montant de "+ reservation.get().getMontantTotalTTC() + " euros.");
				email.setFrom(DtoMailAttributs.FROM);
				email.setSubject(DtoMailAttributs.ANNULATIONSUBJECT);
				email.setTo(reservation.get().getMail());
				emailService.sendMail(email);
		}  
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
			drp.setIdTrajet(idTrajet);
			
			return drp;
		}
		else {
			throw new NotFoundException ("Trajet indisponible à la réservation ou inexistant !");
		}
	}	
	
	@Override
	public void saveReservation(String mail, DtoReservationPlaces drp) throws AddressException {
		if (EmailService.isValidEmailAddress(mail)) {
			
			if (checkifReservationIsPossible(drp.getIdTrajet(), drp.getNbPlacesReservees())) {
				Reservation reservation = new Reservation();
				reservation.setMail(mail);
				reservation.setEtatPaiement(true);
				reservation.setEtatReservationClient("Valide");
				reservation.setMontantTotalTTC(drp.getMontantTotalTTC());
				reservation.setNbPlacesReservees(drp.getNbPlacesReservees());
				
				List<Reservation> reservations = trajetRepository.findById(drp.getIdTrajet()).get().getReservations();
	            reservations.add(reservation);
	            trajetRepository.findById(drp.getIdTrajet()).get().setReservations(reservations);
				
				reservationRepository.save(reservation);
				DtoMail email = new DtoMail();
				email.setContent("Nous vous confirmons la réservation de "+drp.getNbPlacesReservees()+" place(s) pour le trajet Saint-Etienne/Lyon pour un montant total de "+ drp.getMontantTotalTTC() + " euros TTC.");
				email.setFrom(DtoMailAttributs.FROM);
				email.setSubject(DtoMailAttributs.CONFIRMATIONSUBJECT);
				email.setTo(mail);
				emailService.sendMail(email);
				
				trajetService.updateNbPlacesTrajet(drp.getIdTrajet());
				trajetService.updateEtatReservation(drp.getIdTrajet());
		}
		else {
			throw new NotFoundException ("Trajet indisponible à la réservation ou inexistant !");
		}
		}
		else {
				throw new AddressException();
			}
		}

	public DtoDemandedePaiement askReservationFromUser(DtodemandeAchat dtodemandeAchat) {
		
		if(checkifReservationIsPossible(dtodemandeAchat)
				&& trajetRepository.findRide(dtodemandeAchat.getDate(), dtodemandeAchat.getHeureDepart()).get().getEtatReservation().equals("disponible")
				&& !trajetRepository.findRide(dtodemandeAchat.getDate(), dtodemandeAchat.getHeureDepart()).get().getEtatTrajet().equals("annulé")
				&& !trajetRepository.findRide(dtodemandeAchat.getDate(), dtodemandeAchat.getHeureDepart()).get().getEtatTrajet().equals("terminé")) {
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

	public boolean checkifReservationIsPossible(long idTrajet, int nbPlaceAReserver) {
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